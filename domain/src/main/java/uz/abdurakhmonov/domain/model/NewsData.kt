package uz.abdurakhmonov.domain.model

data class NewsData(
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