package uz.assh_abdurakhmonov.newsplus.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite.PopularTab
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu.MenuTab
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification.HotNewsTab
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.profile.ProfileTab
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search.SearchTab
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.Color
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

object HomeScreen : Screen {
    private fun readResolve(): Any = HomeScreen

    @Composable
    override fun Content() {
        HomeContainer()
    }
}

@Composable
fun HomeContainer() {
    TabNavigator(tab = MenuTab) {
        Scaffold(
            content = {
                Box(modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.onPrimary)
                ) {
                    CurrentTab()
                }

            }, bottomBar = {
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 16.dp),
                    containerColor = Color.bgBottomNav

                ) {
                    ItemBottomTab(tab = HotNewsTab)
                    ItemBottomTab(tab = SearchTab)
                    ItemBottomTabMenu(tab = MenuTab)
                    ItemBottomTab(tab = PopularTab)
                    ItemBottomTab(tab = ProfileTab)
                }
            }
        )
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun RowScope.ItemBottomTab(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    Column(
        modifier = Modifier
            .height(60.dp)
            .weight(1f)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                tabNavigator.current = tab
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = tab.options.icon!!,
            contentDescription = "",
            tint = Color.primary,
            modifier = Modifier.size(22.dp),
        )
        Text(
            text = tab.options.title,
            fontSize = 13.sp,
            fontFamily = lato,
            color = Color.primary,
            fontWeight = FontWeight.Normal,
            maxLines = 1
        )
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun RowScope.ItemBottomTabMenu(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    Column(
        modifier = Modifier
            .padding(top = 6.dp)
            .height(64.dp)
            .weight(1f)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                tabNavigator.current = tab
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color.primary, shape = RoundedCornerShape(50.dp))
                .shadow(elevation = 4.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = "",
                tint = Color.onPrimary,
                modifier = Modifier
                    .size(34.dp)
                    .border(1.5.dp, color = Color.onPrimary, shape = RoundedCornerShape(32.dp))
                    .padding(6.dp),
            )
        }

    }
}


@Preview
@Composable
fun Preview() {
    HomeContainer()
}