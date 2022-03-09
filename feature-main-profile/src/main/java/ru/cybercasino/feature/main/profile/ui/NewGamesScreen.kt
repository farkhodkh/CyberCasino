package ru.cybercasino.feature.main.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.R

@Composable
fun NewGamesScreen(
    imagesList: List<Int> = emptyList(),
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(color = DarkBlue)
            .padding(top = 15.dp, start = 28.dp, end = 28.dp, bottom = 15.dp)
            .then(modifier)
    ) {

        val boxWithConstraintsScope = this

        val imageModifier = Modifier
            .height(100.dp)
            .width(boxWithConstraintsScope.maxWidth / 2 - 5.dp)
            .clip(RoundedCornerShape(15.dp))


        imagesList.forEachIndexed { index, imageId ->
            when (index) {
                0 -> Image(
                    painter = painterResource(id = imageId),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(Alignment.TopStart)
                        .then(imageModifier)
                )
                1 -> Image(
                    painter = painterResource(id = imageId),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.TopEnd)
                        .then(imageModifier)
                )
                2 -> Image(
                    painter = painterResource(id = imageId),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .align(Alignment.BottomStart)
                        .then(imageModifier)
                )
                3 -> Image(
                    painter = painterResource(id = imageId),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.BottomEnd)
                        .then(imageModifier)
                )
            }
        }
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NewGamesScreenPreview() {

    NewGamesScreen(
        imagesList = listOf(
            R.drawable.kozino_background_1,
            R.drawable.kozino_background_2,
            R.drawable.kozino_background_3,
            R.drawable.kozino_background_4
        ),
    )
}