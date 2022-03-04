package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White
import androidx.compose.ui.graphics.Color
import ru.cybercasino.ui.Black

@Composable
fun ListDivider(
    dividerLabel: String,
    labelColor: Color = White,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_devider_start),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(0.3f, fill = true)
        )

        AutoSizeText(
            text = dividerLabel,
            maxLines = 1,
            suggestedFontSizes = listOf(18.sp, 16.sp, 14.sp),
            style = TextStyle(
                color = labelColor,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .wrapContentWidth()
                .weight(0.4f)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_devider_end),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(0.3f, fill = true)
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ListDividerPreview() {
    ListDivider(dividerLabel = "Новости", labelColor = Black)
}