package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.domain.model.NewsData

interface SearchContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
    data class UIState(val list: List<NewsData> = emptyList())
    data class SideEffect(val message:String)
    sealed interface Intent{
        data class ClickItem(val data: NewsData):Intent
        data class ClickSearch(val query:String):Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: String)
    }
}