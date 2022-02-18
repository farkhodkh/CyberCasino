package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.cybercasino.ui.R

@Composable
fun RegisterWithSocialNetworkScreen(
    labelResourceId: Int
) {

    Text(
        modifier = Modifier
            .layoutId("joinWithSocialNetworks"),
        text = stringResource(id = labelResourceId)
    )

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