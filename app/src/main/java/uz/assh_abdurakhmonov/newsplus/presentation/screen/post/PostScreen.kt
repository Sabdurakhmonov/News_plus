package uz.assh_abdurakhmonov.newsplus.presentation.screen.post

import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.core.screen.Screen


import cafe.adriel.voyager.hilt.getViewModel
data class PostScreen(val data: String) : Screen {

    @Composable
    override fun Content() {
        val viewModel:PostContract.ViewModel = getViewModel<PostViewModel>()
        PostScreenContent(data = data, intent = viewModel::onEventDispatchers)
    }

}


@Composable
fun PostScreenContent(
    data:String,
    intent:(PostContract.Intent)->Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        WebViewPage(url = data?:"")
    }
}

@Composable
fun WebViewPage(url: String) {
    // AndroidView yordamida WebView integratsiyasi
    AndroidView(
        modifier = Modifier.padding(top = 32.dp).fillMaxSize(),
        factory = {
        android.webkit.WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.loadUrl(url)
    })
}