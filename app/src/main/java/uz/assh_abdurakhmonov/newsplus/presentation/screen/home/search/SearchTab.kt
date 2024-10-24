package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search

import android.annotation.SuppressLint
import android.content.Context
import android.inputmethodservice.InputMethodService.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.assh_abdurakhmonov.newsplus.R
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.AppInputTextComponent
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.HotNewsItem
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.Color
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.inter
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

object SearchTab : Tab {
    private fun readResolve(): Any = SearchTab
    override val options: TabOptions
        @Composable
        get() {
            val icon =
                rememberVectorPainter(image = ImageVector.run { vectorResource(id = R.drawable.ic_search) })
            return TabOptions(
                index = 1u,
                icon = icon,
                title = "search"
            )
        }

    @Composable
    override fun Content() {
        val viewModel: SearchContract.ViewModel = getViewModel<SearchViewModel>()
        val uiState = viewModel.collectAsState()
        SearchTabContent(uiState, viewModel::onEventDispatcher)
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SearchTabContent(
    uiState: State<SearchContract.UIState>,
    intent: (SearchContract.Intent) -> Unit
) {
    val search = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Search for new ones",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                fontFamily = lato,
                modifier = Modifier.weight(1f),
            )
        }
        AppInputTextComponent(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            hint = "search",
            text = search.value,
            minHeight = 50.dp,
            onValueChange = {
                if (search.value.length < 20) {
                    search.value = it
                }
            },
            endIcon = {
                val keyboardController = LocalSoftwareKeyboardController.current
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(color = Color.primary)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            keyboardController?.hide()
                            intent.invoke(SearchContract.Intent.ClickSearch(search.value))
                        }
                        .padding(8.dp),
                    tint = Color.onPrimary

                )
            },
        )
        if (uiState.value.list.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                items(uiState.value.list.size) {
                    if (uiState.value.list[it].urlToImage != null && uiState.value.list[it].author != null) {
                        HotNewsItem(
                            data = uiState.value.list[it],
                            listener = {intent.invoke(SearchContract.Intent.ClickItem(it))}
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.empty_news),
                        contentDescription = "icon",
                        modifier = Modifier.height(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "News is not available!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = inter,
                        color = Color.secondary,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

            }
        }
    }
}
