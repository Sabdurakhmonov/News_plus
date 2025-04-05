package uz.abdurakhmonov.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun providesNewsApi(retrofit: Retrofit): uz.abdurakhmonov.data.remote.network.api.NewsApi = retrofit.create(
        uz.abdurakhmonov.data.remote.network.api.NewsApi::class.java)
}