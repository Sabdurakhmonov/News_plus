package uz.assh_abdurakhmonov.newsplus.remote.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.assh_abdurakhmonov.newsplus.remote.network.response.LocalData

@Database(entities = [LocalData::class], version = 1)
abstract class NewsDataBase :RoomDatabase(){
    abstract fun dao():NewsDao
}