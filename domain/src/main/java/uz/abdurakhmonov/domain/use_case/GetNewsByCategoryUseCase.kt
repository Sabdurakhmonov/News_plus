package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import uz.abdurakhmonov.domain.model.NewsData
import uz.abdurakhmonov.data.repository.NewsRepository
import uz.abdurakhmonov.domain.utils.toDomain
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val newsRepository: NewsRepository
){
    operator fun invoke(category: String):Flow<Result<List<NewsData>>> = flow{
        newsRepository.getNewsByCategory(category).collectLatest {
            it.onSuccess {
                emit(Result.success(it.map { it.toDomain() }))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }
}