package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.presentation.screen.post.PostScreen
import uz.abdurakhmonov.data.remote.network.response.Articles
import javax.inject.Inject

class SearchDirection @Inject constructor(
    private val appNavigator: AppNavigator
):SearchContract.Direction {
    override suspend fun nextToInfo(data: String) {
        appNavigator.push(PostScreen(data))
    }
}