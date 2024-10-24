package uz.assh_abdurakhmonov.newsplus.domian.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.internal.NopCollector.emit
import uz.assh_abdurakhmonov.newsplus.data.CategoryData
import uz.assh_abdurakhmonov.newsplus.domian.repository.MenuRepository
import uz.assh_abdurakhmonov.newsplus.remote.local.SharedHelper
import uz.assh_abdurakhmonov.newsplus.remote.local.room.NewsDao
import uz.assh_abdurakhmonov.newsplus.remote.network.api.NewsApi
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Type
import uz.assh_abdurakhmonov.newsplus.utils.toHot
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val api:NewsApi,
    private val local:SharedHelper,
    private val dao: NewsDao
): MenuRepository {

    override fun getAllNews(): Flow<Result<Pair<List<Articles>, List<Articles>>>> = flow {
        val result1 = api.getAllNews(local.getKey())
        val result2 = api.getAllHotNews(local.getKey())
        if(result2.isSuccessful&&result1.isSuccessful&&result1.body()!=null&&result2.body()!=null){
            val pair = Pair(result1.body()!!.articles,result2.body()!!.articles)
            emit(Result.success(pair))
        }

    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)


    override fun getNewsByCategory(category: CategoryData): Flow<Result<List<Articles>>> = flow{
        val result = api.getNewsByCategory(local.getKey(),category.category)
        if(result.isSuccessful&&result.body()!=null){
            emit(Result.success(result.body()!!.articles))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getAllHotNews(): Flow<Result<List<Articles>>> = flow{
        val result =api.getAllHotNews(local.getKey())
        if(result.isSuccessful&&result.body()!=null){
            emit(Result.success(result.body()!!.articles))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getNewsByQuery(query: String): Flow<Result<List<Articles>>> = flow{
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

    override fun initDataAll(): Flow<Result<Unit>> {

    }

    override fun initDataHots(): Flow<Result<Unit>> {

    }

    override fun initDataByCategory(): Flow<Result<Unit>> = flow {
        val tabList = listOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology"
        )
        tabList.forEach {category->
            val result = api.getNewsByCategory(local.getKey(),category)
            if(result.isSuccessful&&result.body()!=null){
                dao.addData(result.body()!!.articles.map { it.toHot().copy(type = Type.ALL.value, category = category) })

            }
        }
        emit(Result.success(Unit))
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun initDataPopular(): Flow<Result<Unit>> = flow {
        val result = api.getPopularNews(local.getKey())
        if(result.isSuccessful&&result.body()!=null){

            emit(Result.success(Unit))
        }
    }


}