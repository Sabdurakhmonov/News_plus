package uz.abdurakhmonov.domain.repository

import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun getAllNews(): Flow<Result<Pair<List<uz.abdurakhmonov.data.remote.network.response.Articles>, List<uz.abdurakhmonov.data.remote.network.response.Articles>>>>
    fun getNewsByCategory(category:String):Flow<Result<List<uz.abdurakhmonov.data.remote.network.response.Articles>>>
    fun getAllHotNews():Flow<Result<List<uz.abdurakhmonov.data.remote.network.response.Articles>>>
    fun getNewsByQuery(query: String):Flow<Result<List<uz.abdurakhmonov.data.remote.network.response.Articles>>>
    fun getPopularNews():Flow<Result<List<uz.abdurakhmonov.data.remote.network.response.Articles>>>

    //work
    suspend fun initDataAll()
    suspend fun initDataHots()
    suspend fun initDataByCategory()
    suspend fun initDataPopular()
}