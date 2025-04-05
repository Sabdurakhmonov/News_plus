package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.data.remote.network.response.Articles

interface MenuContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    data class UIState(
        val hotNewsList: List<uz.abdurakhmonov.data.remote.network.response.Articles> = emptyList(),
        val allNewsList: List<uz.abdurakhmonov.data.remote.network.response.Articles> = emptyList()
    )
    data class SideEffect(
        val message:String
    )
    sealed interface Intent{
        data class ClickCategory(val category:String):Intent
        data class ClickItem(val data: uz.abdurakhmonov.data.remote.network.response.Articles):Intent
        data object ClickSeeMore:Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: String)
        suspend fun nextToSeeMore()
    }
}