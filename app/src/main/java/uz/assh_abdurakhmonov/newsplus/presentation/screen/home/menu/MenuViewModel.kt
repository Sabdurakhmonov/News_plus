package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.abdurakhmonov.domain.use_case.GetAllNewsUseCase
import uz.abdurakhmonov.domain.use_case.GetHotNewsUseCase
import uz.abdurakhmonov.domain.use_case.GetNewsByCategoryUseCase
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getHotNewsUseCase: GetHotNewsUseCase,
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase,
    private val direction: MenuContract.Direction
):ViewModel(),MenuContract.ViewModel {

    init{
        intent {
            getAllNewsUseCase.invoke().onEach {
                it.onSuccess {news->
                   reduce { state.copy(allNewsList = news) }
                }
            }.launchIn(viewModelScope)

            getHotNewsUseCase.invoke().onEach {
                it.onSuccess {
                    reduce { state.copy(hotNewsList = it) }
                }
            }.launchIn(viewModelScope)
        }

    }

    override fun onEventDispatchers(intent: MenuContract.Intent) = intent {
        when(intent){
            is MenuContract.Intent.ClickCategory->{
                if(intent.category!="All news"){
                    getNewsByCategoryUseCase.invoke(intent.category).onEach {
                        it.onSuccess {
                            reduce { state.copy(allNewsList = it.subList(20,it.size-1), hotNewsList = it.subList(0,20)) }
                        }
                    }.launchIn(viewModelScope)
                }else{
                    getAllNewsUseCase.invoke().onEach {
                        it.onSuccess {news->
                            reduce { state.copy(allNewsList = news) }
                        }
                    }.launchIn(viewModelScope)

                    getHotNewsUseCase.invoke().onEach {
                        it.onSuccess {
                            reduce { state.copy(hotNewsList = it) }
                        }
                    }.launchIn(viewModelScope)
                }

            }
            is MenuContract.Intent.ClickItem->{
                direction.nextToInfo(intent.data.url?:"")
            }
            is MenuContract.Intent.ClickSeeMore->{

            }
        }
    }

    override val container = container<MenuContract.UIState, MenuContract.SideEffect>(MenuContract.UIState())
}