package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu

import org.orbitmvi.orbit.ContainerHost
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles

interface MenuContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    data class UIState(
        val hotNewsList: List<Articles> = emptyList(),
        val allNewsList: List<Articles> = emptyList()
    )
    data class SideEffect(
        val message:String
    )
    sealed interface Intent{
        data class ClickCategory(val category:String):Intent
        data class ClickItem(val data:Articles):Intent
        data object ClickSeeMore:Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: Articles)
        suspend fun nextToSeeMore()
    }
}