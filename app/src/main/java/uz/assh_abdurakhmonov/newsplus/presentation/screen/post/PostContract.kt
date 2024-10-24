package uz.assh_abdurakhmonov.newsplus.presentation.screen.post

import org.orbitmvi.orbit.ContainerHost

interface PostContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
    }
    data class UIState(
        val idString: String = "id"
    )
    data class SideEffect(
        val message:String
    )
    sealed interface Intent{

        data object ClickBack:Intent
    }
    interface Direction{
        suspend fun back()
    }
}