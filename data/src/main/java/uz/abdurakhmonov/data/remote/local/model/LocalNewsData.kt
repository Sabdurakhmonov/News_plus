package uz.abdurakhmonov.data.remote.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalNewsData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val type:String?,
    val sourceName: String?,
    val author:String?,
    val category:String,
    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val content:String?
)