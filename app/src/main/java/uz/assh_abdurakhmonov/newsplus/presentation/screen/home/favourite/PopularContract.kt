package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.domain.model.NewsData

interface PopularContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
    data class UIState(val populars:List<NewsData> = emptyList())
    data class SideEffect(val message:String)
    sealed interface Intent{
        data class ClickItem(val data: NewsData):Intent
    }
    interface Direction{
        suspend fun nextToInfo(data:String)
    }
}