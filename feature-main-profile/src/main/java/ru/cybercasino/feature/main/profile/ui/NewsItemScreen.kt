@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.main.profile.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R

@Composable
fun NewsItemScreen(
    imageId: Int = R.drawable.news_item_background,
    dateAndTime: Date,
    newsHeader: String,
    newsContent: String,
    modifier: Modifier = Modifier

) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(color = DarkBlue)
            .padding(top = 15.dp, start = 28.dp, end = 28.dp, bottom = 15.dp)
            .then(modifier)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Red
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        )

        Text(
            text = getFormattedDate(dateAndTime),
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                .align(alignment = Alignment.Start),
            color = BlueGrey,
            fontSize = 14.sp
        )

        Text(
            text = newsHeader,
            modifier = Modifier
                .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                .align(alignment = Alignment.Start),
            color = White,
            fontSize = 18.sp
        )

        Text(
            text = newsContent,
            modifier = Modifier
                .layoutId("newsContentText")
                .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                .align(alignment = Alignment.Start),
            color = White,
            fontSize = 14.sp,
            maxLines = if (expanded) Int.MAX_VALUE else 1
        )

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Cursive,
                textDecoration = TextDecoration.Underline,
            )
            ){
                append(text = stringResource(id = R.string.read_full_text))
            }
        }
        ClickableText(
            text = annotatedText,
            modifier = Modifier
                .padding(top = 28.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
                .align(alignment = Alignment.Start),
            onClick = {expanded = !expanded}
        )
    }
}

private fun getFormattedDate(date: Date) : String {
    val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm")

    return sdf.format(date)
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NewsItemScreenPreview() {
    NewsItemScreen(
        imageId = R.drawable.news_item_background,
        dateAndTime = Calendar.getInstance().time,
        newsHeader = "Новость номер один",
        newsContent = "Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один Новость номер один"
    )
}