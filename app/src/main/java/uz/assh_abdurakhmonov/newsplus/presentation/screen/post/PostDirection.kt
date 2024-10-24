package uz.assh_abdurakhmonov.newsplus.presentation.screen.post

import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import javax.inject.Inject

class PostDirection @Inject constructor(
    private val appNavigator: AppNavigator
):PostContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }
}