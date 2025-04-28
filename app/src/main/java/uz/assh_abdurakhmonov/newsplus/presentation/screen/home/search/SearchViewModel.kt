package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.abdurakhmonov.domain.repository.NewsRepository
import uz.abdurakhmonov.domain.use_case.GetNewsByQueryUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsByQueryUseCase: GetNewsByQueryUseCase,
    private val direction: SearchContract.Direction
):ViewModel(),SearchContract.ViewModel {
    override fun onEventDispatcher(intent: SearchContract.Intent) = intent {
        when(intent){
            is SearchContract.Intent.ClickItem->{
                direction.nextToInfo(intent.data.url?:"")
            }
            is SearchContract.Intent.ClickSearch->{
                getNewsByQueryUseCase.invoke(intent.query).onEach {
                    it.onSuccess {
                        reduce { state.copy(list = it) }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    override val container = container<SearchContract.UIState, SearchContract.SideEffect>(SearchContract.UIState())
}