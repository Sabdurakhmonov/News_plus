package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.assh_abdurakhmonov.newsplus.domian.repository.MenuRepository
import javax.inject.Inject

@HiltViewModel
class HotNewsViewModel @Inject constructor(
    private val repository: MenuRepository,
    private val direction: HotNewsContract.Direction
):ViewModel(),HotNewsContract.ViewModel {
    init {
        repository.getAllHotNews().onEach {
            it.onSuccess {
                intent { reduce { state.copy(hotNews = it) }}
            }.onSuccess {

            }
        }.launchIn(viewModelScope)
    }
    override fun onEventDispatcher(intent: HotNewsContract.Intent) = intent {
        when(intent){
            is HotNewsContract.Intent.ClickItem->{
                direction.nextToInfo(intent.data)
            }
        }
    }

    override val container = container<HotNewsContract.UIState, HotNewsContract.SideEffect>(HotNewsContract.UIState())
}