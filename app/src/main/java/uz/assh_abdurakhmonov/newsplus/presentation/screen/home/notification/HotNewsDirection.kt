package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.screen.post.PostScreen
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.PopularItem
import javax.inject.Inject

class HotNewsDirection @Inject constructor(
    private val appNavigator: AppNavigator
):HotNewsContract.Direction {
    override suspend fun nextToInfo(data: Articles) {
        appNavigator.push(PostScreen(data))
    }
}