package uz.assh_abdurakhmonov.newsplus.presentation.screen.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.assh_abdurakhmonov.newsplus.R
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.Color
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

object SplashScreen:Screen {
    private fun readResolve(): Any = SplashScreen

    @Composable
    override fun Content() {

    }
}


@SuppressLint("Range")
@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.primary)
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(id = R.drawable.empty_news),
            contentDescription = "icon",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(0.02f))
        Text(
            text = "News Plus",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = lato,
            color = Color.onPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(0.02f))
        Text(
            text = "Latest news and world news",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = lato,
            color = Color.onPrimary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.35f))
        Text(
            text = "GITA ACADEMY",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = lato,
            color = Color.secondary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(0.04f))
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}