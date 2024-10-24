package uz.assh_abdurakhmonov.newsplus.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.assh_abdurakhmonov.newsplus.remote.network.api.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun providesNewsApi(retrofit: Retrofit):NewsApi = retrofit.create(NewsApi::class.java)
}