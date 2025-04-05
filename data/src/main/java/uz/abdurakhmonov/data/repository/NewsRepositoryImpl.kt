package uz.abdurakhmonov.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.abdurakhmonov.data.remote.local.room.NewsDao
import uz.abdurakhmonov.data.remote.local.shared.SharedHelper
import uz.abdurakhmonov.data.remote.network.response.Articles
import uz.abdurakhmonov.data.remote.network.response.NewsType
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: uz.abdurakhmonov.data.remote.network.api.NewsApi,
    private val local: SharedHelper,
    private val dao: NewsDao
) : NewsRepository {

    override fun getAllNews(): Flow<Result<Pair<List<Articles>, List<Articles>>>> = flow {
        val res1 = api.getAllNews(local.getKey())
        val res2 = api.getAllHotNews(local.getKey())
        if(res2.isSuccessful&&res1.isSuccessful){
            emit(Result.success(Pair(res1.body()!!.articles,res2.body()!!.articles)))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)


    override fun getNewsByCategory(category: String): Flow<Result<List<Articles>>> = flow {
        val result = api.getNewsByCategory(local.getKey(),category)
        if(result.body()!=null&&result.isSuccessful){
            emit(Result.success(result.body()!!.articles))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getAllHotNews(): Flow<Result<List<Articles>>> = flow {
        val result = api.getAllHotNews(local.getKey())
        if(result.isSuccessful&&result.body()!=null){
            emit(Result.success(result.body()!!.articles))
        }

    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getNewsByQuery(query: String): Flow<Result<List<Articles>>> = flow {
        val result = api.searchByQuery(local.getKey(),query)
        if(result.isSuccessful&&result.body()!=null){
            emit(Result.success(result.body()!!.articles))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getPopularNews(): Flow<Result<List<Articles>>> = flow {

        val result = api.getPopularNews(local.getKey())
        if(result.isSuccessful&&result.body()!=null){
            emit(Result.success(result.body()!!.articles))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)



    //init news
    override suspend fun initDataAll() = withContext(Dispatchers.IO){
        val result = api.getAllHotNews(local.getKey())
        if (result.isSuccessful && result.body() != null) {
            dao.addData(result.body()!!.articles.map { it.toLocal().copy(type = NewsType.ALL.value) })
        }
    }

    override suspend fun initDataHots() = withContext(Dispatchers.IO){
        val result = api.getAllHotNews(local.getKey())
        if (result.isSuccessful && result.body() != null) {
            dao.addData(result.body()!!.articles.map { it.toLocal().copy(type = NewsType.HOT.value) })
        }
    }

    override suspend fun initDataByCategory() = withContext(Dispatchers.IO){
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
                    it.toLocal().copy(type = NewsType.ALL.value, category = category)
                })

            }
        }
    }

    override suspend fun initDataPopular() = withContext(Dispatchers.IO) {
        val result = api.getPopularNews(local.getKey())
        if (result.isSuccessful && result.body() != null) {
            dao.addData(result.body()!!.articles.map {
                it.toLocal().copy(type = NewsType.POPULAR.value)
            })
        }
    }
}