package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R

/**
 * Register with social network
 */
@Composable
fun RegisterWithSocialNetworkScreen(
    labelResourceId: Int
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .layoutId("joinWithSocialNetworks")
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Gray
            )

            Text(
                text = " " + stringResource(id = labelResourceId) + " ",
                modifier = Modifier
                    .background(color = DarkBlue)
                    .align(Alignment.Center),
                color = White,
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            IconButton(
                onClick = { },
                modifier = Modifier
                    .layoutId("facebookIcon")
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_fb),
                    contentDescription = "Facebook"
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .layoutId("googleIcon")
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = "Google"
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .layoutId("tgIcon")
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_tg),
                    contentDescription = "Telegram"
                )
            }
        }
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegisterWithSocialNetworkScreenPreview() {
    RegisterWithSocialNetworkScreen(labelResourceId = R.string.or_join_by_text)
}