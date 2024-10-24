package uz.assh_abdurakhmonov.newsplus.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.assh_abdurakhmonov.newsplus.remote.local.room.NewsDao
import uz.assh_abdurakhmonov.newsplus.remote.local.room.NewsDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @[Provides Singleton]
    fun providesShared(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("myData", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun providesNewsDatabase(@ApplicationContext context: Context): NewsDataBase =
        Room.databaseBuilder(context, NewsDataBase::class.java, "news").build()

    @[Provides Singleton]
    fun providesNewsDao(dataBase: NewsDataBase):NewsDao = dataBase.dao()
}