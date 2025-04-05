package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.domain.model.NewsData

interface MenuContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
    }
    data class UIState(
        val hotNewsList: List<NewsData> = emptyList(),
        val allNewsList: List<NewsData> = emptyList()
    )
    data class SideEffect(
        val message:String
    )
    sealed interface Intent{
        data class ClickCategory(val category:String):Intent
        data class ClickItem(val data: NewsData):Intent
        data object ClickSeeMore:Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: String)
        suspend fun nextToSeeMore()
    }
}