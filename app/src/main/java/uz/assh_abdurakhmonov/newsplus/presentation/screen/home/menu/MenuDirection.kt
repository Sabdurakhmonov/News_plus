package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.screen.post.PostScreen
import javax.inject.Inject

class MenuDirection @Inject constructor(
    private val appNavigator: AppNavigator
) :MenuContract.Direction{
    override suspend fun nextToInfo(data: Articles) {
        appNavigator.push(PostScreen(data))
    }

    override suspend fun nextToSeeMore() {

    }
}