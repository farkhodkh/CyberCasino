package ru.cybercasino.feature.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.CyberButton

@Composable
fun AuthorizationScreen() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

    if (!state.isAuthorised) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(top = 24.dp),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { scaffoldState.drawerState.open() }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = "Menu"
                            )
                        }
                    },
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.ic_logo),
                                    contentDescription = ""
                                )
                                CyberButton("Регистрация", onClick = {} )
                            }
                        }
                    }
                )
            },
            content = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Cyber Casino",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                }
            },
        )
    } else {

    }
}


@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AuthorizationScreenPreview() {
    AuthorizationScreen()
}