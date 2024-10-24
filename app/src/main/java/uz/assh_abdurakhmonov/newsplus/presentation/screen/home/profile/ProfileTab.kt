package uz.assh_abdurakhmonov.newsplus.presentation.screen.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.assh_abdurakhmonov.newsplus.R
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.Color
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.inter
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

object ProfileTab : Tab {
    private fun readResolve(): Any = ProfileTab
    override val options: TabOptions
        @Composable
        get() {
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_info))
            return TabOptions(
                index = 4u,
                icon = icon,
                title = "profile"
            )
        }

    @Composable
    override fun Content() {
        ProfileTabContent()
    }
}

@Composable
fun ProfileTabContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {}

        Image(
            painter = painterResource(id = R.drawable.img_2),
            contentDescription = "img",
            modifier = Modifier
                .clip(RoundedCornerShape(72.dp))
                .size(72.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Info news ",
            fontFamily = inter,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "This application is about news and information and API newsapi.org" +
                    "from and I have used a free API in this application, you can keep up with the daily news and search for the news you need and like. I made this app for learning in an android course. I wrote this program in jetpack compose",
            fontSize = 18.sp, modifier = Modifier.padding(24.dp),
            textAlign = TextAlign.Justify
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ProfileTabPreview() {
    ProfileTabContent()
}