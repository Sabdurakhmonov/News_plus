package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.FavouriteItem
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.HotNewsItem
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.HotNewsShimmer
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.PopularItem
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.inter
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

object PopularTab:Tab {
    private fun readResolve(): Any = PopularTab
    override val options: TabOptions
        @Composable
        get(){
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_popular))
            return TabOptions(
                index = 3u,
                icon = icon,
                title = "popular"
            )
        }

    @Composable
    override fun Content() {
        val viewModel:PopularContract.ViewModel = getViewModel<PopularViewModel>()
        val uiState = viewModel.collectAsState()
        PopularTabContent(uiState,viewModel::onEventDispatcher)
    }
}

@Composable
fun PopularTabContent(
    uiState: State<PopularContract.UIState>,
    intent:(PopularContract.Intent)->Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Popular news",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                fontFamily = lato,
                modifier = Modifier.weight(1f),
            )
        }
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            if(uiState.value.populars.isEmpty()){
                items(8){
                    HotNewsShimmer()
                }
            }else{
                items(uiState.value.populars.size){
                    if(uiState.value.populars[it].urlToImage!=null&&uiState.value.populars[it].author!=null){
                        PopularItem(
                            data = uiState.value.populars[it],
                            listener = {intent.invoke(PopularContract.Intent.ClickItem(it))}
                        )
                    }
                }
            }

        }
    }
}
