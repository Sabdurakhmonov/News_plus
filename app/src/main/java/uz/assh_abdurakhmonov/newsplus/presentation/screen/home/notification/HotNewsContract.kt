package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.data.remote.network.response.Articles

interface HotNewsContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
    data class UIState(val hotNews:List<uz.abdurakhmonov.data.remote.network.response.Articles> = emptyList())
    data class SideEffect(val message:String)
    interface Intent{
        data class ClickItem(val data: uz.abdurakhmonov.data.remote.network.response.Articles):Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: String)
    }
}