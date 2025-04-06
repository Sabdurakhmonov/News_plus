package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.abdurakhmonov.domain.model.NewsData
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsByQuery @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String): Flow<Result<List<NewsData>>> =
        newsRepository.getNewsByQuery(query)
}