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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.Black
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.R

@Composable
fun JackpotScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DarkBlue)
            .padding(top = 15.dp, start = 28.dp, end = 28.dp, bottom = 15.dp)
            .then(modifier)
    ) {
        val imageModifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            Image(
                painter = painterResource(id = R.drawable.background_jackpot_gold),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .then(imageModifier)
            )
            Text(
                text = stringResource(id = R.string.jackpot_label_gold),
                color = Black,
                fontSize = 28.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(top = 50.dp, start = 140.dp)
                    .align(Alignment.TopStart)
            )

            Text(
                text = "$ 201 025",
                color = Black,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                ),
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(top = 90.dp, start = 140.dp)
                    .align(Alignment.TopStart)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
                .height(160.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            Image(
                painter = painterResource(id = R.drawable.background_jackpot_silver),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .then(imageModifier)
            )
            Text(
                text = stringResource(id = R.string.jackpot_label_silver),
                color = Black,
                fontSize = 28.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(top = 50.dp, start = 140.dp)
                    .align(Alignment.TopStart)
            )

            Text(
                text = "$ 201 025",
                color = Black,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                ),
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(top = 90.dp, start = 140.dp)
                    .align(Alignment.TopStart)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
                .height(280.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_jackpot_bronze),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .height(160.dp)
                    .then(imageModifier)
            )
            Text(
                text = stringResource(id = R.string.jackpot_label_bronze),
                color = Black,
                fontSize = 28.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(top = 50.dp, start = 140.dp)
                    .align(Alignment.TopStart)
            )

            Text(
                text = "$ 201 025",
                color = Black,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                ),
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(top = 90.dp, start = 140.dp)
                    .align(Alignment.TopStart)
            )

            Image(
                painter = painterResource(id = R.drawable.background_jackpot_bottom),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun JackpotScreenPreview() {
    JackpotScreen()
}