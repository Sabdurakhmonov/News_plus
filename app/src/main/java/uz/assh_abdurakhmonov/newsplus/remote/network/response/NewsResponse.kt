package uz.assh_abdurakhmonov.newsplus.remote.network.response

data class NewsResponse(
    val status:String?,
    val totalResults:Int?,
    val articles:List<Articles>
)