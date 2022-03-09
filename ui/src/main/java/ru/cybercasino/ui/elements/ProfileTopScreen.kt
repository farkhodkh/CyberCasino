package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.Green
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White

@Composable
fun ProfileTopScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(id = R.drawable.background_main_play),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            ProfileTitle(
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 16.dp)
            )

            CyberButton(
                title = stringResource(id = R.string.play_label),
                titleSize = 16.sp,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .padding(start = 28.dp, end = 28.dp)
            )
        }
    }
}

@Composable
private fun ProfileTitle(
    modifier: Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                )
            ) {
                append(stringResource(id = R.string.win_and_have_fun_with_cybercasino))
            }
            withStyle(
                style = SpanStyle(
                    color = Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            ) {
                append("\n" + stringResource(id = R.string.cybercasino))
            }
        },
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .then(modifier),
        fontSize = 32.sp,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
    )
}
