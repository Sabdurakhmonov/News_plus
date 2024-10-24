package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite

import org.orbitmvi.orbit.ContainerHost
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles

interface PopularContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
    data class UIState(val populars:List<Articles> = emptyList())
    data class SideEffect(val message:String)
    sealed interface Intent{
        data class ClickItem(val data: Articles):Intent
    }
    interface Direction{
        suspend fun nextToInfo(data:Articles)
    }
}