package uz.abdurakhmonov.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.domain.model.NewsData


interface NewsRepository {
    fun getAllNews(): Flow<Result<List<NewsData>>>
    fun getNewsByCategory(category: String): Flow<Result<List<NewsData>>>
    fun getHotNews(): Flow<Result<List<NewsData>>>
    fun getNewsByQuery(query: String): Flow<Result<List<NewsData>>>
    fun getPopularNews(): Flow<Result<List<NewsData>>>

    //mode state
    fun getModeState():Flow<Result<Boolean>>
    fun setModeState():Flow<Result<Unit>>

}