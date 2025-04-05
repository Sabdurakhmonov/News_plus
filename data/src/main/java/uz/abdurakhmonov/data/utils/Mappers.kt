package uz.abdurakhmonov.data.utils

import uz.abdurakhmonov.data.remote.local.model.LocalNewsData
import uz.abdurakhmonov.data.remote.network.response.Articles
import uz.abdurakhmonov.domain.model.NewsData

fun LocalNewsData.toDomain(): NewsData =
    NewsData(
        id = id,
        type = type,
        sourceName = sourceName,
        author = author,
        category = category,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )

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