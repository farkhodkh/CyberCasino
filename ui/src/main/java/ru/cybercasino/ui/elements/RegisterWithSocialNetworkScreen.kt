package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.R

@Composable
fun RegisterWithSocialNetworkScreen(
    labelResourceId: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .layoutId("joinWithSocialNetworks"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (labelResourceId != 0 )
            Text(
                modifier = Modifier,
                text = stringResource(id = labelResourceId)
            )

        Row(
            modifier = Modifier.padding(top = 24.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
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