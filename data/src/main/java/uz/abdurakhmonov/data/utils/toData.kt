package uz.abdurakhmonov.data.utils

import uz.abdurakhmonov.data.remote.local.model.LocalNewsData
import uz.abdurakhmonov.data.remote.network.response.Articles

fun Articles.toData(): LocalNewsData = LocalNewsData(
    id = 0,
    type = null,
    sourceName = source?.name,
    author = author,
    category = "",
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)