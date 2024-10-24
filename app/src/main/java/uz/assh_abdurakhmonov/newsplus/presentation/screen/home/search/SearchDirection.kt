package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.screen.post.PostScreen
import javax.inject.Inject

class SearchDirection @Inject constructor(
    private val appNavigator: AppNavigator
):SearchContract.Direction {
    override suspend fun nextToInfo(data: Articles) {
        appNavigator.push(PostScreen(data))
    }
}