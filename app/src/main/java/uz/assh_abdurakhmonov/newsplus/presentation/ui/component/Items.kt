package uz.assh_abdurakhmonov.newsplus.presentation.ui.component

import android.media.Image
import android.text.TextUtils.substring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import uz.assh_abdurakhmonov.newsplus.R
import uz.abdurakhmonov.data.remote.network.response.Articles
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.Color
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.inter
import uz.assh_abdurakhmonov.newsplus.presentation.ui.theme.lato

@Preview(showBackground = true)
@Composable
private fun ItemPreview() {

}


@Composable
fun AppInputTextComponent(
    modifier: Modifier,
    hint: String,
    text: String,
    minHeight: Dp,
    value: ((String) -> Unit)? = null,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable() (() -> Unit)? = null,
    endIcon: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
) {
    BasicTextField(
        value = text, onValueChange = onValueChange,
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(72.dp))
            .fillMaxWidth(1f)
            .defaultMinSize(minHeight = minHeight)
            .border(width = 1.4.dp, color = Color.primary, shape = RoundedCornerShape(56.dp))
            .background(color = Color.onPrimary, shape = RoundedCornerShape(32.dp)),
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter
        ),
        cursorBrush = SolidColor(Color.primary),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        visualTransformation = visualTransformation,

        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(minHeight),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.invoke()
                    Box {
                        if(text.isEmpty()){
                            Text(
                                text = hint,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.secondary,
                                modifier = Modifier.focusable(false),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = inter
                            )
                        }
                        innerTextField()
                    }

                    Spacer(modifier = Modifier.weight(0.2f))

                    endIcon?.invoke()
                }
            }
        }
    )
}

@Composable
fun FavouriteItem() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(60.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = "icon",
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(60.dp))
                .border(width = 0.5.dp, color = Color.primary, shape = RoundedCornerShape(70.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxHeight(0.7f)
                .weight(1f)
        ) {
            Text(
                text = "New post from Apple.lnc",
                fontSize = 15.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.weight(0.5f),
                color = Color.primary
            )
            Text(
                text = "Do check out Apple's news product line-up",
                fontSize = 13.sp,
                fontFamily = lato,
                color = Color.secondary,
                maxLines = 1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            contentDescription = "icon",
            tint = Color.favColor
        )
    }

}

@Composable
fun NotificationItem() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = "icon",
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(60.dp))
                .border(width = 0.5.dp, color = Color.primary, shape = RoundedCornerShape(70.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxHeight(0.7f)
                .weight(1f)
        ) {
            Text(
                text = "New post from Apple.lnc",
                fontSize = 15.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.weight(0.5f),
                color = Color.primary
            )
            Text(
                text = "Do check out Apple's news product line-up",
                fontSize = 13.sp,
                fontFamily = lato,
                color = Color.secondary,
                maxLines = 1
            )
        }
    }
}


@Composable
fun HotNewsItem(data: uz.abdurakhmonov.data.remote.network.response.Articles, listener: (uz.abdurakhmonov.data.remote.network.response.Articles) -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .height(115.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { listener.invoke(data) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val url =
                "https://firebasestorage.googleapis.com/v0/b/bookapp-fd7a6.appspot.com/o/Untitled.jpeg?alt=media&token=9cf5fc64-e573-4100-b66d-8f1e9e6c02ba"

            Image(
                painter = rememberAsyncImagePainter(
                    model = data.urlToImage, error = painterResource(
                        id = R.drawable.img_1
                    )
                ),
                contentDescription = "img",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
                    .padding(top = 2.dp)
            ) {
                Text(
                    text = data.source?.name?:data.title?: "",
                    fontFamily = inter,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 6.dp),
                    maxLines = 1
                )

                Text(
                    text = data.description ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = inter,
                    maxLines = 3,
                    modifier = Modifier.weight(1f)
                )

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.author ?: "BBC news",
                fontWeight = FontWeight.Bold,
                fontFamily = inter,
                fontSize = 14.sp,
                color = Color.primary,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .weight(1f),
                maxLines = 1
            )

            Text(
                text = data.publishedAt?.substring(0, 10) ?: "",
                fontFamily = lato,
                color = Color.secondary,
                fontSize = 14.sp,
                maxLines = 1,
            )

        }
    }

}

@Composable
fun PopularItem(data: uz.abdurakhmonov.data.remote.network.response.Articles, listener: (uz.abdurakhmonov.data.remote.network.response.Articles) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable { listener.invoke(data) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = data.urlToImage,
                error = painterResource(id = R.drawable.img_1)
            ),
            contentDescription = "img",
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .size(120.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .padding(top = 4.dp)
        ) {

            Text(
                text = data.description ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = inter,
                maxLines = 4,
                modifier = Modifier.weight(0.7f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.author
                        ?: (data.source?.name?: ""),
                    fontWeight = FontWeight.Normal,
                    fontFamily = inter,
                    fontSize = 14.sp,
                    color = Color.secondary,
                    modifier = Modifier
                        .padding()
                        .weight(0.5f),
                    maxLines = 1
                )

                Text(
                    text = data.publishedAt?.substring(0, 10) ?: "",
                    fontFamily = lato,
                    color = Color.secondary,
                    fontSize = 14.sp,
                    maxLines = 1,
                )

            }
        }
    }
}

@Composable
fun NewsItem(data: uz.abdurakhmonov.data.remote.network.response.Articles, listener: (uz.abdurakhmonov.data.remote.network.response.Articles) -> Unit) {
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
                .clickable { listener.invoke(data) }
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = data.urlToImage, error = painterResource(
                        id = R.drawable.img_1
                    )
                ),
                contentDescription = "img",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (data.author != null) {
                    Text(
                        text = data.author,
                        fontSize = 13.sp,
                        color = Color.primary,
                        modifier = Modifier
                            .background(
                                Color.bgItemText,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(8.dp),
                        maxLines = 1
                    )
                } else if (data.source != null) {
                    Text(
                        text = data.source.name?:"",
                        fontSize = 13.sp,
                        color = Color.primary,
                        modifier = Modifier
                            .background(
                                Color.bgItemText,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(8.dp),
                        maxLines = 1
                    )
                }
            }

        }

        Text(
            text = data.description ?: "",
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 12.dp),
            fontSize = 16.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun TabItem(select: Boolean, index: Int, data: String, click: (String, Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.5.dp, color = Color.primary, shape = RoundedCornerShape(12.dp))
            .clickable { click.invoke(data, index) }
            .background(color = if (select) Color.primary else Color.onPrimary)
            .padding(horizontal = 16.dp)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = data,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = inter,
            color = if (select) Color.onPrimary else Color.primary,
        )
    }
}

@Composable
fun NewsItems(data: uz.abdurakhmonov.data.remote.network.response.Articles, listener: (uz.abdurakhmonov.data.remote.network.response.Articles) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { listener.invoke(data) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = data.urlToImage),
            contentDescription = "img",
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(80.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .padding(top = 6.dp)
        ) {

            Text(
                text = data.description ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = inter,
                maxLines = 2,
                modifier = Modifier.weight(0.7f)
            )
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.author ?: "",
                    fontWeight = FontWeight.Normal,
                    fontFamily = inter,
                    fontSize = 14.sp,
                    color = Color.secondary,
                    modifier = Modifier
                        .padding()
                        .weight(0.5f),
                    maxLines = 1
                )

                Text(
                    text = data.publishedAt?.substring(0, 10) ?: "",
                    fontFamily = lato,
                    color = Color.secondary,
                    fontSize = 14.sp,
                    maxLines = 1,
                )

            }
        }
    }
}
