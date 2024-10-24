package uz.assh_abdurakhmonov.newsplus.remote.network.response

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

@Entity
data class LocalData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val type:String = "hot",
    val sourceName: String?,
    val author:String?,
    val category:String,
    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishedAt:String?,
)

enum class Type(val value:String){
    HOT("hot"),POPULAR("popular"),ALL("all")
}
