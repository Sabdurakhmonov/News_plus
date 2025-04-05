package uz.abdurakhmonov.data.remote.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.data.remote.network.response.LocalData

@Dao
interface NewsDao {
    @Query("SELECT*FROM LocalData WHERE type=:type")
    fun getAllDataByType(type:String):Flow<List<uz.abdurakhmonov.data.remote.network.response.LocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(data:List<uz.abdurakhmonov.data.remote.network.response.LocalData>)

    @Query("DElETE FROM LocalData")
    fun clearBase()

    @Query("SELECT*FROM localdata WHERE author LIKE '%' || :query || '%'")
    fun search(query: String):Flow<List<uz.abdurakhmonov.data.remote.network.response.LocalData>>
}