package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.orbitmvi.orbit.compose.collectAsState
import uz.assh_abdurakhmonov.newsplus.R
import uz.assh_abdurakhmonov.newsplus.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.HotNewsShimmer
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.MenuAllShimmer
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.MenuHotShimmer
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.NewsItem
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.NewsItems
import uz.assh_abdurakhmonov.newsplus.presentation.ui.component.TabItem
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.Color
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.inter
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

object MenuTab : Tab {
    private fun readResolve(): Any = MenuTab
    override val options: TabOptions
        @Composable
        get() {
            val icon =
                rememberVectorPainter(image = ImageVector.run { vectorResource(id = R.drawable.ic_home_simple) })
            return TabOptions(
                index = 2u,
                icon = icon,
                title = ""
            )
        }

    @Composable
    override fun Content() {
        val viewModel: MenuContract.ViewModel = getViewModel<MenuViewModel>()
        val uiState = viewModel.collectAsState()
        MenuTabContent(uiState, viewModel::onEventDispatchers)
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuTabContent(
    uiState: State<MenuContract.UIState>,
    intent: (MenuContract.Intent) -> Unit
) {
    val selector = remember {
        mutableIntStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "News Plus",
                fontSize = 28.sp,
                fontFamily = lato,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "icon",
                modifier = Modifier
                    .size(25.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color.primary,
                        shape = RoundedCornerShape(25.dp)
                    )
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(1f),
        ) {
            val tabList = listOf(
                "All news",
                "business",
                "entertainment",
                "general",
                "health",
                "science",
                "sports",
                "technology"
            )
            item {
                LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
                    items(tabList.size) {
                        TabItem(
                            select = if (it == selector.intValue) { true } else { false },
                            index = it,
                            data = tabList[it],
                            click = { data, index ->
                                intent.invoke(MenuContract.Intent.ClickCategory(data))
                                selector.intValue = index
                            }
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hot news",
                        fontWeight = FontWeight.Bold,
                        fontFamily = inter,
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "See more", color = Color.secondary, fontSize = 14.sp)
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = "icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.secondary
                    )

                }
            }
            item {
                if (uiState.value.hotNewsList.isNotEmpty()) {
                    AwesomeCarousel(
                        uiState = uiState,
                        listener = {intent.invoke(MenuContract.Intent.ClickItem(it))}
                    )
                } else {
                    MenuHotShimmer()
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Latest news",
                        fontWeight = FontWeight.Bold,
                        fontFamily = inter,
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "See more", color = Color.secondary, fontSize = 14.sp)
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = "icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.secondary
                    )

                }
            }
            if (uiState.value.allNewsList.isNotEmpty()) {
                items(uiState.value.allNewsList.size) {
                    if (uiState.value.allNewsList[it].urlToImage != null && uiState.value.allNewsList[it].description != null) {
                        NewsItems(
                            data = uiState.value.allNewsList[it],
                            listener = {intent.invoke(MenuContract.Intent.ClickItem(it))}
                        )

                    }
                }
            } else {
                items(10) {
                    MenuAllShimmer()
                }
            }

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AwesomeCarousel(
    uiState: State<MenuContract.UIState>,
    pageCount: Int = 10,
    pagerState: PagerState = rememberPagerState(pageCount = { 10 }),
    autoScrollDuration: Long = 3000L,
    listener:(Articles)->Unit
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember { mutableStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = autoScrollDuration)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    HorizontalPager(state = pagerState) {
        if (uiState.value.hotNewsList[it].urlToImage != null && uiState.value.hotNewsList[it].description != null) {
            NewsItem(
                data = uiState.value.hotNewsList[it],
                listener = { listener.invoke(it)}
            )
        }else{
            NewsItem(
                data = uiState.value.hotNewsList[19-it],
                listener = {listener.invoke(it)}
            )
        }
    }
}



