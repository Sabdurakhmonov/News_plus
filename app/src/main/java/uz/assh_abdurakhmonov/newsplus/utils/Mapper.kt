package uz.assh_abdurakhmonov.newsplus.utils

import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.remote.network.response.LocalData
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Type

fun Articles.toHot(): LocalData = LocalData(
    0,
    "",
    sourceName = source?.name,
    author = author,
    title = title,
    description = description,
    url = url,
    category = "",
    urlToImage = urlToImage,
    publishedAt = publishedAt,
)