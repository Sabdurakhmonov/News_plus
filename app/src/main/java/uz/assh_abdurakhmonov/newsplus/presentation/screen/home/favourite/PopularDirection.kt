package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.presentation.screen.post.PostScreen

import javax.inject.Inject

class PopularDirection @Inject constructor(
    private val appNavigator: AppNavigator
):PopularContract.Direction {
    override suspend fun nextToInfo(data: String) {
        appNavigator.push(PostScreen(data))
    }
}