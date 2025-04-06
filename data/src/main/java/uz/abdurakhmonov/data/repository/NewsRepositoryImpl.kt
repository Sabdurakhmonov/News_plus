package uz.abdurakhmonov.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.abdurakhmonov.data.remote.local.room.NewsDao
import uz.abdurakhmonov.data.remote.local.shared.SharedHelper
import uz.abdurakhmonov.data.remote.network.response.NewsType
import uz.abdurakhmonov.data.utils.toData
import uz.abdurakhmonov.data.utils.toDomain
import uz.abdurakhmonov.data.utils.toHours
import uz.abdurakhmonov.domain.model.NewsData
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: uz.abdurakhmonov.data.remote.network.api.NewsApi,
    private val local: SharedHelper,
    private val dao: NewsDao
) : NewsRepository {

    override fun getAllNews(): Flow<Result<List<NewsData>>> = flow {

        val time = System.currentTimeMillis() - local.getTimeUpdate()

        if (time.toHours() > 1) {
            initDataAll()
        }
        dao.getAllDataByType(NewsType.ALL.value).collectLatest {
            emit(Result.success(it.map { it.toDomain() }))
        }

    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)


    override fun getNewsByCategory(category: String): Flow<Result<List<NewsData>>> = flow {
        dao.getNewsByCategory(category).collectLatest {
            emit(Result.success(it.map { it.toDomain() }))
        }

    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getHotNews(): Flow<Result<List<NewsData>>> = flow {

        dao.getAllDataByType(NewsType.HOT.value).collectLatest {
            emit(Result.success(it.map { it.toDomain() }))
        }

    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getNewsByQuery(query: String): Flow<Result<List<NewsData>>> = flow {
        val result = api.searchByQuery(local.getKey(), query)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(result.body()!!.articles.map { it.toData().toDomain() }))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getPopularNews(): Flow<Result<List<NewsData>>> = flow {
        dao.getAllDataByType(NewsType.POPULAR.value).collectLatest {
            emit(Result.success(it.map { it.toDomain() }))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)


    override fun getModeState(): Flow<Result<Boolean>> = flow {
        emit(Result.success(local.getModeState()))
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun setModeState(): Flow<Result<Unit>> = flow{
        if(local.setModeState()){
            emit(Result.success(Unit))
        }else{
            emit(Result.failure(Exception("error")))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)


    //init news
    private suspend fun initDataAll() = withContext(Dispatchers.IO) {

        dao.clearBase()

        local.setTimeUpdate(System.currentTimeMillis())

        val result = api.getAllHotNews(local.getKey())
        if (result.isSuccessful && result.body() != null) {
            dao.addData(result.body()!!.articles.map {
                it.toData().copy(type = NewsType.ALL.value)
            })
        }

        initDataHots()
        initDataPopular()
        initDataByCategory()
    }

    private suspend fun initDataHots() = withContext(Dispatchers.IO) {
        val result = api.getAllHotNews(local.getKey())
        if (result.isSuccessful && result.body() != null) {
            dao.addData(result.body()!!.articles.map {
                it.toData().copy(type = NewsType.HOT.value)
            })
        }
    }

    private suspend fun initDataByCategory() = withContext(Dispatchers.IO) {
        val tabList = listOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology"
        )
        tabList.forEach { category ->
            val result = api.getNewsByCategory(local.getKey(), category)
            if (result.isSuccessful && result.body() != null) {
                dao.addData(result.body()!!.articles.map {
                    it.toData().copy(type = NewsType.ALL.value, category = category)
                })
            }
        }
    }

    private suspend fun initDataPopular() = withContext(Dispatchers.IO) {
        val result = api.getPopularNews(local.getKey())
        if (result.isSuccessful && result.body() != null) {
            dao.addData(result.body()!!.articles.map {
                it.toData().copy(type = NewsType.POPULAR.value)
            })
        }
    }
}