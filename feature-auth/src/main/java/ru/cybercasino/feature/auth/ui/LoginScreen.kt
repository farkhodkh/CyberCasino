package ru.cybercasino.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.ui.AuthorizationScreen
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.R

/**
 * Login screen
 */
@Composable
fun LoginScreen(modifier: Modifier) {
    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
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
    } else {
        AuthorizationScreen()
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(Modifier)
}