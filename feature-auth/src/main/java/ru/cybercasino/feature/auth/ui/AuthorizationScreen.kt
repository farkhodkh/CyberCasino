@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.auth.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.LightBlue
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.elements.CyberButtonWithBorder

/**
 * Authorization screen
 */
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
                            contentAlignment = Alignment.TopCenter,
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.ic_logo),
                                    contentDescription = "",
                                    modifier = Modifier.padding(start = 60.dp)
                                )
                                CyberButton(
                                    stringResource(id = R.string.registration_text),
                                    onClick = {},
                                    Modifier
                                        .padding(top = 14.dp)
                                        .width(142.dp)
                                        .height(21.dp)
                                )
                            }
                        }
                    }
                )
            },
            content = {
                ConstraintLayout(
                    constraintSet = ConstraintSet {
                        val refEnterTitle = createRefFor("enterTitle")
                        val refLoginField = createRefFor("loginField")
                        val refPasswordField = createRefFor("passwordField")
                        val refEnterButton = createRefFor("enterButton")
                        val refForgotPasswordTitle = createRefFor("forgotPasswordTitle")
                        val refJoinWithSocialNetworks = createRefFor("joinWithSocialNetworks")
                        val refFacebookIcon = createRefFor("facebookIcon")
                        val refTgIcon = createRefFor("tgIcon")
                        val refGoogleIcon = createRefFor("googleIcon")

                        constrain(refEnterTitle) {
                            top.linkTo(parent.top, 50.dp)
                            start.linkTo(parent.start, 16.dp)
                        }

                        constrain(refLoginField) {
                            top.linkTo(refEnterTitle.bottom, 50.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                        }

                        constrain(refPasswordField) {
                            top.linkTo(refLoginField.bottom, 20.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                        }
                        constrain(refEnterButton) {
                            top.linkTo(refPasswordField.bottom, 26.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                        }

                        constrain(refForgotPasswordTitle) {
                            top.linkTo(refEnterButton.bottom, 10.dp)
                            end.linkTo(parent.end, 16.dp)
                        }

                        constrain(refJoinWithSocialNetworks) {
                            bottom.linkTo(parent.bottom, 125.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end)
                        }

                        constrain(refFacebookIcon) {
                            top.linkTo(refJoinWithSocialNetworks.bottom, 24.dp)
                            end.linkTo(refGoogleIcon.start, 16.dp)
                        }

                        constrain(refGoogleIcon) {
                            top.linkTo(refJoinWithSocialNetworks.bottom, 24.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                        }

                        constrain(refTgIcon) {
                            top.linkTo(refJoinWithSocialNetworks.bottom, 24.dp)
                            start.linkTo(refGoogleIcon.end, 16.dp)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .layoutId("enterTitle"),
                        text = stringResource(id = R.string.enter_text),
                        fontSize = 32.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    )
                    var emailPhoneText by remember { mutableStateOf(TextFieldValue("")) }

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .layoutId("loginField"),
                        value = emailPhoneText,
                        onValueChange = {
                            emailPhoneText = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.enter_email_or_phone),
                                fontSize = 10.sp,
                                color = White
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enter_email_or_phone),
                                fontSize = 14.sp,
                                color = White
                            )
                        },
                    )

                    var password by rememberSaveable { mutableStateOf("") }
                    var passwordVisibility by remember { mutableStateOf(false) }

                    TextField(
                        modifier = Modifier
                            .layoutId("passwordField")
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                text = stringResource(id = R.string.enter_password),
                                fontSize = 10.sp,
                                color = White
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enter_password),
                                fontSize = 14.sp,
                                color = White
                            )
                        },
                        visualTransformation = if (passwordVisibility)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passwordVisibility)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(imageVector = image, "")
                            }
                        }
                    )

                    CyberButtonWithBorder(
                        title = "Войти",
                        onClick = { /*TODO*/ },
                        Modifier
                            .layoutId("enterButton")
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(44.dp)
                    )

                    TextButton(
                        onClick = {},
                        modifier = Modifier.layoutId("forgotPasswordTitle")
                    ) {
                        Text(
                            stringResource(id = R.string.forget_password),
                            fontSize = 16.sp,
                            color = LightBlue
                        )
                    }
                    Text(
                        modifier = Modifier
                            .layoutId("joinWithSocialNetworks"),
                        text = stringResource(id = R.string.or_join_by_text)
                    )

                    IconButton(onClick = {  },
                        modifier = Modifier
                            .layoutId("facebookIcon")
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_fb),
                            contentDescription = "Facebook"
                        )
                    }

                    IconButton(onClick = {  },
                        modifier = Modifier
                            .layoutId("googleIcon")
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_google),
                            contentDescription = "Google"
                        )
                    }

                    IconButton(onClick = {  },
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
            },
        )
    } else {
        //TODO - add sign in view
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AuthorizationScreenPreview() {
    AuthorizationScreen()
}