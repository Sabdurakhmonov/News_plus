package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search

import org.orbitmvi.orbit.ContainerHost
import uz.abdurakhmonov.data.remote.network.response.Articles

interface SearchContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
    data class UIState(val list: List<uz.abdurakhmonov.data.remote.network.response.Articles> = emptyList())
    data class SideEffect(val message:String)
    sealed interface Intent{
        data class ClickItem(val data: uz.abdurakhmonov.data.remote.network.response.Articles):Intent
        data class ClickSearch(val query:String):Intent
    }
    interface Direction{
        suspend fun nextToInfo(data: String)
    }
}