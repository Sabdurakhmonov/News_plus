package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.domain.model.NewsData

interface HotNewsContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
    data class UIState(val hotNews:List<NewsData> = emptyList())
    data class SideEffect(val message:String)
    interface Intent{
        data class ClickItem(val data: NewsData):Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: String)
    }
}