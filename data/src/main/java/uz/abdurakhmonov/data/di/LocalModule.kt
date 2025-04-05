package uz.abdurakhmonov.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @[Provides Singleton]
    fun providesShared(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("myData", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun providesNewsDatabase(@ApplicationContext context: Context): uz.abdurakhmonov.data.remote.local.room.NewsDataBase =
        Room.databaseBuilder(context, uz.abdurakhmonov.data.remote.local.room.NewsDataBase::class.java, "news").build()

    @[Provides Singleton]
    fun providesNewsDao(dataBase: uz.abdurakhmonov.data.remote.local.room.NewsDataBase): uz.abdurakhmonov.data.remote.local.room.NewsDao = dataBase.dao()
}