package uz.abdurakhmonov.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.domain.model.NewsData


interface NewsRepository {
    fun getAllNews(): Flow<Result<Pair<List<NewsData>, List<NewsData>>>>
    fun getNewsByCategory(category:String):Flow<Result<List<NewsData>>>
    fun getAllHotNews():Flow<Result<List<NewsData>>>
    fun getNewsByQuery(query: String):Flow<Result<List<NewsData>>>
    fun getPopularNews():Flow<Result<List<NewsData>>>

    //work
    suspend fun initDataAll()
    suspend fun initDataHots()
    suspend fun initDataByCategory()
    suspend fun initDataPopular()
}