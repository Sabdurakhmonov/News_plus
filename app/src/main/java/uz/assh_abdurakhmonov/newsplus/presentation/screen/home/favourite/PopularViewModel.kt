package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.abdurakhmonov.domain.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val direction: PopularContract.Direction
):ViewModel(),PopularContract.ViewModel{
    init {
        repository.getPopularNews().onEach {
            it.onSuccess {list->
                intent { reduce { state.copy(populars = list) } }
            }
        }.launchIn(viewModelScope)
    }
    override fun onEventDispatcher(intent: PopularContract.Intent) = intent{
        when(intent){
            is PopularContract.Intent.ClickItem->{
                direction.nextToInfo(intent.data.url?:"")
            }
        }
    }

    override val container = container<PopularContract.UIState, PopularContract.SideEffect>(PopularContract.UIState())
}