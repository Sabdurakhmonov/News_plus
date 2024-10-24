package uz.assh_abdurakhmonov.newsplus.presentation.screen.post

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
@HiltViewModel
class PostViewModel @Inject constructor(
   private val direction: PostContract.Direction
):PostContract.ViewModel,ViewModel() {
    override fun onEventDispatchers(intent: PostContract.Intent) = intent{
        when(intent){
            is PostContract.Intent.ClickBack->{
                direction.back()
            }
        }
    }

    override val container = container<PostContract.UIState, PostContract.SideEffect>(PostContract.UIState())
}

