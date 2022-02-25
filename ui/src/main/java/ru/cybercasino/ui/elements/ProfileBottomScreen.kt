package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import ru.cybercasino.feature.auth.ui.auth.RegisterWithSocialNetworkScreen
import ru.cybercasino.ui.Dark
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White

@Composable
fun ProfileBottomScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 26.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_big),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                ProfileClickableHeaderText(
                    labelId = R.string.games,
                    onItemClick = {}
                )

                ProfileClickableText(
                    labelId = R.string.best_games,
                    onItemClick = {},
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.Start
            ) {

                ProfileClickableHeaderText(
                    labelId = R.string.for_users,
                    onItemClick = {}
                )

                ProfileClickableText(
                    labelId = R.string.FAQ,
                    onItemClick = {},
                    modifier = Modifier.padding(top = 20.dp)
                )

                ProfileClickableText(
                    labelId = R.string.information,
                    onItemClick = {},
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }

        ProfileClickableHeaderText(
            labelId = R.string.for_users,
            onItemClick = {},
            modifier = Modifier.padding(top = 50.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                ProfileClickableText(
                    labelId = R.string.privacy_policy,
                    onItemClick = {},
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                ProfileClickableText(
                    labelId = R.string.user_agreement,
                    onItemClick = {},
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

private val headerTextStyle = SpanStyle(
    color = White,
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold
)

private val textStyle = SpanStyle(
    color = White,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
)

@Composable
fun ProfileClickableHeaderText(
    labelId: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = headerTextStyle
        ) {
            append(text = stringResource(id = labelId))
        }
    }
    ClickableText(
        text = annotatedText,
        modifier = Modifier
            .then(modifier),
        onClick = { onItemClick() }
    )
}

@Composable
fun ProfileClickableText(
    labelId: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = textStyle
        ) {
            append(text = stringResource(id = labelId))
        }
    }

    ClickableText(
        text = annotatedText,
        modifier = Modifier
            .then(modifier),
        onClick = { onItemClick() }
    )
}


@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProfileBottomScreenPreview() {
    ProfileBottomScreen()
}