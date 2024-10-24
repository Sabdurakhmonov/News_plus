package uz.assh_abdurakhmonov.newsplus.domian.repository

import kotlinx.coroutines.flow.Flow
import uz.assh_abdurakhmonov.newsplus.data.CategoryData
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles

interface MenuRepository {
    fun getAllNews():Flow<Result<Pair<List<Articles>,List<Articles>>>>
    fun getNewsByCategory(category:CategoryData):Flow<Result<List<Articles>>>
    fun getAllHotNews():Flow<Result<List<Articles>>>
    fun getNewsByQuery(query: String):Flow<Result<List<Articles>>>
    fun getPopularNews():Flow<Result<List<Articles>>>

    //work
    fun initDataAll():Flow<Result<Unit>>
    fun initDataHots():Flow<Result<Unit>>
    fun initDataByCategory():Flow<Result<Unit>>
    fun initDataPopular():Flow<Result<Unit>>
}