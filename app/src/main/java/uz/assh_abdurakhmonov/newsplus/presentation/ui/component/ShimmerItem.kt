package uz.assh_abdurakhmonov.newsplus.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.valentinilk.shimmer.shimmer
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.inter
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

@Preview(showBackground = true)
@Composable
fun ShimmerPreview() {
    MenuAllShimmer()
}

@Composable
fun HotNewsShimmer() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .height(115.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .shimmer()
                    .clip(RoundedCornerShape(12.dp))
                    .size(80.dp)
                    .background(color = Color.Gray)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
                    .padding(top = 6.dp)
            ) {

                Spacer(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .height(18.dp)
                        .fillMaxWidth(0.5f)
                        .shimmer()
                        .background(color = Color.LightGray)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .height(18.dp)
                        .fillMaxWidth()
                        .shimmer()
                        .background(color = Color.LightGray)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .height(18.dp)
                        .fillMaxWidth()
                        .shimmer()
                        .background(color = Color.LightGray)
                )

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(2.dp))
                    .width(100.dp)
                    .height(18.dp)
                    .shimmer()
                    .background(Color.LightGray)
            )


            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Spacer(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(2.dp))
                    .width(100.dp)
                    .height(18.dp)
                    .shimmer()
                    .background(Color.LightGray)
            )

        }
    }
}

@Composable
fun PopularNewsShimmer() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(120.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .size(120.dp)
                .shimmer()
                .background(Color.LightGray),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .padding(top = 4.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .height(18.dp)
                    .fillMaxWidth()
                    .shimmer()
                    .background(color = Color.LightGray)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .height(18.dp)
                    .fillMaxWidth()
                    .shimmer()
                    .background(color = Color.LightGray)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .height(18.dp)
                    .fillMaxWidth()
                    .shimmer()
                    .background(color = Color.LightGray)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(2.dp))
                        .width(100.dp)
                        .height(18.dp)
                        .shimmer()
                        .background(Color.LightGray)
                )


                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Spacer(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(2.dp))
                        .width(70.dp)
                        .height(18.dp)
                        .shimmer()
                        .background(Color.LightGray)
                )

            }
        }
    }
}

@Composable
fun MenuHotShimmer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(14.dp))
                .fillMaxWidth()
                .height(220.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmer()
                    .background(color = Color.LightGray),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .height(18.dp)
                        .fillMaxWidth(0.4f)
                        .shimmer()
                        .background(color = Color.Gray)
                )
            }

        }

        Spacer(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(vertical = 4.dp)
                .clip(shape = RoundedCornerShape(2.dp))
                .height(18.dp)
                .fillMaxWidth()
                .shimmer()
                .background(color = Color.LightGray)
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(shape = RoundedCornerShape(2.dp))
                .height(18.dp)
                .fillMaxWidth()
                .shimmer()
                .background(color = Color.LightGray)
        )

    }
}

@Composable
fun MenuAllShimmer(){
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(80.dp)
                .shimmer()
                .background(color = Color.LightGray),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .padding(top = 6.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .height(18.dp)
                    .fillMaxWidth()
                    .shimmer()
                    .background(color = Color.LightGray)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .height(18.dp)
                    .fillMaxWidth()
                    .shimmer()
                    .background(color = Color.LightGray)
            )
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .height(18.dp)
                        .width(100.dp)
                        .shimmer()
                        .background(color = Color.LightGray)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)

                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .height(18.dp)
                        .width(80.dp)
                        .shimmer()
                        .background(color = Color.LightGray)
                )


            }
        }
    }
}
