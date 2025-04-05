package uz.abdurakhmonov.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abdurakhmonov.data.repository.NewsRepositoryImpl
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @[Binds Singleton]
    fun bindsMenuRepository(impl: NewsRepositoryImpl): NewsRepository
}