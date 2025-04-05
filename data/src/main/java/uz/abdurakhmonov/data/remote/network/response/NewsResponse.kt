package uz.abdurakhmonov.data.remote.network.response

data class NewsResponse(
    val status:String?,
    val totalResults:Int?,
    val articles:List<Articles>
)