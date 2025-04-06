package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.abdurakhmonov.domain.model.NewsData
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke():Flow<Result<Pair<List<NewsData>,List<NewsData>>>> = newsRepository.getAllNews()
}