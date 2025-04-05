package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.presentation.screen.post.PostScreen

import javax.inject.Inject

class HotNewsDirection @Inject constructor(
    private val appNavigator: AppNavigator
):HotNewsContract.Direction {
    override suspend fun nextToInfo(data: String) {
        appNavigator.push(PostScreen(data))
    }
}