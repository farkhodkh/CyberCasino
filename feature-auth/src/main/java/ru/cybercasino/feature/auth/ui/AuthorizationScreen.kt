@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.auth.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White
import ru.cybercasino.ui.elements.CyberButton

@Composable
fun AuthorizationScreen() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val (refEntertitle, refTitle, refDescription) = FocusRequester.createRefs()

    val enterTitle = ConstrainedLayoutReference(refEntertitle)

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
                                CyberButton(
                                    stringResource(id = R.string.registration_text),
                                    onClick = {},
                                    Modifier
                                        .width(120.dp)
                                        .height(41.dp)
                                )
                            }
                        }
                    }
                )
            },
            content = {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(top = 50.dp, start = 25.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .constrainAs(
                            ref = enterTitle
                        ) {
                            top.linkTo(parent.top)
                        }) {
                        Text(
                            stringResource(id = R.string.enter_text),
                            fontSize = 32.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            ),
                            color = White
                        )

                        TextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("Enter Email") }
                        )

                    }
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