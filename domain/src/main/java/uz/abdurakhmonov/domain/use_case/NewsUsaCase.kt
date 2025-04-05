package uz.abdurakhmonov.domain.use_case

import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

class NewsUsaCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
}