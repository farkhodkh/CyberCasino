@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.elements.CyberButton

@Composable
fun RegistrationScreen(
    onEnterClickListener: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

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
                            painter = painterResource(id = ru.cybercasino.ui.R.drawable.ic_menu),
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
                                painter = painterResource(ru.cybercasino.ui.R.drawable.ic_logo),
                                contentDescription = "",
                                modifier = Modifier.padding(start = 60.dp)
                            )
                            CyberButton(
                                stringResource(id = ru.cybercasino.ui.R.string.enter_text),
                                onClick = {
                                    onEnterClickListener()
                                },
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
//            ConstraintLayout(
//                constraintSet = ConstraintSet {
//                    val refEnterTitle = createRefFor("enterTitle")
//                    val refLoginField = createRefFor("loginField")
//                    val refPasswordField = createRefFor("passwordField")
//                    val refEnterButton = createRefFor("enterButton")
//                    val refForgotPasswordTitle = createRefFor("forgotPasswordTitle")
//                    val refJoinWithSocialNetworks = createRefFor("joinWithSocialNetworks")
//                    val refFacebookIcon = createRefFor("facebookIcon")
//                    val refTgIcon = createRefFor("tgIcon")
//                    val refGoogleIcon = createRefFor("googleIcon")
//
//                    constrain(refEnterTitle) {
//                        top.linkTo(parent.top, 50.dp)
//                        start.linkTo(parent.start, 16.dp)
//                    }
//
//                    constrain(refLoginField) {
//                        top.linkTo(refEnterTitle.bottom, 50.dp)
//                        start.linkTo(parent.start, 16.dp)
//                        end.linkTo(parent.end, 16.dp)
//                    }
//
//                    constrain(refPasswordField) {
//                        top.linkTo(refLoginField.bottom, 20.dp)
//                        start.linkTo(parent.start, 16.dp)
//                        end.linkTo(parent.end, 16.dp)
//                    }
//                    constrain(refEnterButton) {
//                        top.linkTo(refPasswordField.bottom, 26.dp)
//                        start.linkTo(parent.start, 16.dp)
//                        end.linkTo(parent.end, 16.dp)
//                    }
//
//                    constrain(refForgotPasswordTitle) {
//                        top.linkTo(refEnterButton.bottom, 10.dp)
//                        end.linkTo(parent.end, 16.dp)
//                    }
//
//                    constrain(refJoinWithSocialNetworks) {
//                        bottom.linkTo(parent.bottom, 125.dp)
//                        start.linkTo(parent.start, 16.dp)
//                        end.linkTo(parent.end)
//                    }
//
//                    constrain(refFacebookIcon) {
//                        top.linkTo(refJoinWithSocialNetworks.bottom, 24.dp)
//                        end.linkTo(refGoogleIcon.start, 16.dp)
//                    }
//
//                    constrain(refGoogleIcon) {
//                        top.linkTo(refJoinWithSocialNetworks.bottom, 24.dp)
//                        start.linkTo(parent.start, 16.dp)
//                        end.linkTo(parent.end, 16.dp)
//                    }
//
//                    constrain(refTgIcon) {
//                        top.linkTo(refJoinWithSocialNetworks.bottom, 24.dp)
//                        start.linkTo(refGoogleIcon.end, 16.dp)
//                    }
//                },
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Text(
//                    modifier = Modifier
//                        .layoutId("enterTitle"),
//                    text = stringResource(id = ru.cybercasino.ui.R.string.enter_text),
//                    fontSize = 32.sp,
//                    style = TextStyle(
//                        fontWeight = FontWeight.Normal,
//                        textAlign = TextAlign.Center
//                    )
//                )
//                var emailPhoneText by remember { mutableStateOf(TextFieldValue("")) }
//
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, end = 16.dp)
//                        .layoutId("loginField"),
//                    value = emailPhoneText,
//                    onValueChange = {
//                        emailPhoneText = it
//                    },
//                    label = {
//                        Text(
//                            text = stringResource(id = ru.cybercasino.ui.R.string.enter_email_or_phone),
//                            fontSize = 10.sp,
//                            color = White
//                        )
//                    },
//                    placeholder = {
//                        Text(
//                            text = stringResource(id = ru.cybercasino.ui.R.string.enter_email_or_phone),
//                            fontSize = 14.sp,
//                            color = White
//                        )
//                    },
//                )
//
//                var password by rememberSaveable { mutableStateOf("") }
//                var passwordVisibility by remember { mutableStateOf(false) }
//
//                TextField(
//                    modifier = Modifier
//                        .layoutId("passwordField")
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, end = 16.dp),
//                    value = password,
//                    onValueChange = { password = it },
//                    label = {
//                        Text(
//                            text = stringResource(id = ru.cybercasino.ui.R.string.enter_password),
//                            fontSize = 10.sp,
//                            color = White
//                        )
//                    },
//                    placeholder = {
//                        Text(
//                            text = stringResource(id = ru.cybercasino.ui.R.string.enter_password),
//                            fontSize = 14.sp,
//                            color = White
//                        )
//                    },
//                    visualTransformation = if (passwordVisibility)
//                        VisualTransformation.None else PasswordVisualTransformation(),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                    trailingIcon = {
//                        val image = if (passwordVisibility)
//                            Icons.Filled.Visibility
//                        else Icons.Filled.VisibilityOff
//
//                        IconButton(onClick = {
//                            passwordVisibility = !passwordVisibility
//                        }) {
//                            Icon(imageVector = image, "")
//                        }
//                    }
//                )
//
//                CyberButtonWithBorder(
//                    title = "Войти",
//                    onClick = { /*TODO*/ },
//                    Modifier
//                        .layoutId("enterButton")
//                        .padding(start = 16.dp, end = 16.dp)
//                        .fillMaxWidth()
//                        .height(44.dp)
//                )
//
//                TextButton(
//                    onClick = {},
//                    modifier = Modifier.layoutId("forgotPasswordTitle")
//                ) {
//                    Text(
//                        stringResource(id = ru.cybercasino.ui.R.string.forget_password),
//                        fontSize = 16.sp,
//                        color = LightBlue
//                    )
//                }
//                Text(
//                    modifier = Modifier
//                        .layoutId("joinWithSocialNetworks"),
//                    text = stringResource(id = ru.cybercasino.ui.R.string.or_join_by_text)
//                )
//
//                IconButton(
//                    onClick = { },
//                    modifier = Modifier
//                        .layoutId("facebookIcon")
//                        .clip(CircleShape)
//                ) {
//                    Image(
//                        painter = painterResource(ru.cybercasino.ui.R.drawable.ic_fb),
//                        contentDescription = "Facebook"
//                    )
//                }
//
//                IconButton(
//                    onClick = { },
//                    modifier = Modifier
//                        .layoutId("googleIcon")
//                        .clip(CircleShape)
//                ) {
//                    Image(
//                        painter = painterResource(ru.cybercasino.ui.R.drawable.ic_google),
//                        contentDescription = "Google"
//                    )
//                }
//
//                IconButton(
//                    onClick = { },
//                    modifier = Modifier
//                        .layoutId("tgIcon")
//                        .clip(CircleShape)
//                ) {
//                    Image(
//                        painter = painterResource(ru.cybercasino.ui.R.drawable.ic_tg),
//                        contentDescription = "Telegram"
//                    )
//                }
//
//            }
        },
    )
}