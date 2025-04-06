package uz.abdurakhmonov.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class SetModeUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke():Flow<Result<Unit>> = flow {  }
}