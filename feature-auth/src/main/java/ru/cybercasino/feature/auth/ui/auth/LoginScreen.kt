package ru.cybercasino.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.ui.AuthorizationScreen
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel

/**
 * Login screen
 * @param onRegisterClickListener on
 * @param modifier - Modifier
 */
@Suppress("UnusedPrivateMember")
@Composable
fun LoginScreen(
    modifier: Modifier,
    onRegisterClickListener: () -> Unit
) {
    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

    if (!state.isAuthorised) {
        AuthorizationScreen(onRegisterClickListener)
    } else {
        //TODO - Start the App
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(Modifier, {})
}