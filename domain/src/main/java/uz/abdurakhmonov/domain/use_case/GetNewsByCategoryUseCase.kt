package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.abdurakhmonov.domain.model.NewsData
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val newsRepository: NewsRepository
){
    operator fun invoke(category: String):Flow<Result<List<NewsData>>> =newsRepository.getNewsByCategory(category)
}