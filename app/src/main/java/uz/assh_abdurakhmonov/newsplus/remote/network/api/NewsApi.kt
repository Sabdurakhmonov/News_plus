package uz.assh_abdurakhmonov.newsplus.remote.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uz.assh_abdurakhmonov.newsplus.remote.network.response.NewsResponse

interface NewsApi {
    @GET("everything")
    suspend fun getAllNews(
        @Header("X-Api-Key") apiKey: String,
        @Query("q") query: String = "All"
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun getNewsByCategory(
        @Header("X-Api-Key") apiKey: String,
        @Query("q") query: String
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun searchByQuery(
        @Header("X-Api-Key") apiKey: String,
        @Query("q") query: String
    ): Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getAllHotNews(
        @Header("X-Api-Key") apiKey: String,
        @Query("q") query: String = "All"
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun getPopularNews(
        @Header("X-Api-Key") apiKey: String,
        @Query("q") query: String = "BBC",
        @Query("sortBy") sort: String = "popularity"
    ):Response<NewsResponse>
}