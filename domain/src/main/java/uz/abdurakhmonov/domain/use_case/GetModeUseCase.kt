package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.abdurakhmonov.data.repository.NewsRepository
import javax.inject.Inject

class GetModeUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke():Flow<Result<Boolean>> = newsRepository.getModeState()
}