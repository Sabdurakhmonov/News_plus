package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.domain.model.NewsData
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class GetPopularNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
){
    operator fun invoke():Flow<Result<List<NewsData>>> = newsRepository.getPopularNews()
}