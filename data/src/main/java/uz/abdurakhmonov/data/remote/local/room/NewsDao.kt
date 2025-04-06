package uz.abdurakhmonov.data.remote.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.data.remote.local.model.LocalNewsData

@Dao
interface NewsDao {

    @Query("SELECT*FROM LocalNewsData WHERE type=:type")
    fun getAllDataByType(type: String): Flow<List<LocalNewsData>>


    @Query("SELECT*FROM LocalNewsData WHERE category=:category")
    fun getNewsByCategory(category: String): Flow<List<LocalNewsData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(data: List<LocalNewsData>)

    @Query("DElETE FROM LocalNewsData")
    fun clearBase()

    @Query("SELECT*FROM LocalNewsData WHERE author LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<LocalNewsData>>
}