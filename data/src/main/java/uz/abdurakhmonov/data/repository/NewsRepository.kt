package uz.abdurakhmonov.data.repository

import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.data.remote.local.model.LocalNewsData

interface NewsRepository {
    fun getAllNews(): Flow<Result<List<LocalNewsData>>>
    fun getNewsByCategory(category: String): Flow<Result<List<LocalNewsData>>>
    fun getHotNews(): Flow<Result<List<LocalNewsData>>>
    fun getNewsByQuery(query: String): Flow<Result<List<LocalNewsData>>>
    fun getPopularNews(): Flow<Result<List<LocalNewsData>>>

    //mode state
    fun getModeState(): Flow<Result<Boolean>>
    fun setModeState(): Flow<Result<Unit>>

}