package uz.abdurakhmonov.data.remote.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.abdurakhmonov.data.remote.network.response.LocalData

@Database(entities = [uz.abdurakhmonov.data.remote.network.response.LocalData::class], version = 1)
abstract class NewsDataBase :RoomDatabase(){
    abstract fun dao(): NewsDao
}