package ru.cybercasino.feature.main.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R

@Composable
fun LastWinnersScreen(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {

        val boxWithConstraintsScope = this

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkBlue)
                .padding(top = 15.dp, start = 28.dp, end = 28.dp, bottom = 15.dp)
        ) {
            val itemWidth = boxWithConstraintsScope.maxWidth/2-10.dp

            WinnerScreen(itemWidth, modifier = Modifier
                .padding(end = 16.dp)
                .weight(0.5f))

            WinnerScreen(itemWidth, modifier = Modifier
                .weight(0.5f))
        }
    }
}

@Composable
fun WinnerScreen(
    maxWidth: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .width(maxWidth)
            .then(modifier)
    ) {
        Image(
            painter = painterResource(id = R.drawable.kozino_background_1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
        )

        Text(
            text = "Победитель",
            color = White,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
            ),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 6.dp, start = 15.dp)
        )

        Text(
            text = "1000 USD",
            color = Yellow,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
            ),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 4.dp, start = 15.dp)
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LasrWinnersScreenPreview() {
    LastWinnersScreen()
}