package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.cybercasino.ui.R

/**
 * Application root screen
 * @param modifier - Modifier
 */
@Composable
fun RootScreen(
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.background_loading_screen),
            contentDescription = "",
            modifier = Modifier.matchParentSize()
        )
        Image(
            painter = painterResource(R.drawable.bachground_cover),
            contentDescription = "",
        )
    }
}