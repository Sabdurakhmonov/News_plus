package uz.assh_abdurakhmonov.newsplus.remote.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.assh_abdurakhmonov.newsplus.remote.network.response.LocalData

@Dao
interface NewsDao {
    @Query("SELECT*FROM LocalData WHERE type=:type")
    fun getAllDataByType(type:String):Flow<List<LocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(data:List<LocalData>)

    @Query("DElETE FROM LocalData")
    fun clearBase():Flow<Boolean>
}