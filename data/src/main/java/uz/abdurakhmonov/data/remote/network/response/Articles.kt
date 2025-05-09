package uz.abdurakhmonov.data.remote.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Articles(
    val source: Source?,
    val author:String?,
    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val content:String?
)
data class Source(
    val id:String?,
    val name:String?
)


