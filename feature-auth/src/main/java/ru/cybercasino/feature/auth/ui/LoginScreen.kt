@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.auth.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
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
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.ui.auth.RegisterWithSocialNetworkScreen
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.feature.auth.viewmodel.PasswordVerificationType
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBarRegistration
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.elements.CyberButtonWithBorder
import ru.cybercasino.ui.utils.defaultCountryData
import ru.cybercasino.ui.utils.getCountriesList

/**
 * Authorization screen
 * @param onClickListener on registration button click listener
 */
@Composable
fun LoginScreen(
    onClickListener: () -> Unit,
    onRegisterClickListener: () -> Unit,
    goToProfileScreen: () -> Unit
) {
    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBarRegistration(
                buttonLabelTextId = R.string.registration_text,
                onButtonClickListener = onClickListener
            )
        },
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refEnterTitle = createRefFor("enterTitle")
                    //val refLoginField = createRefFor("loginField")
                    val refTabRowField = createRefFor("tabRowField")
                    val refEmailField = createRefFor("emailField")
                    val refCountriesDropdownMenu = createRefFor("countriesDropdownMenu")
                    val refCountriesCodeField = createRefFor("countriesCodeField")
                    val refPhoneField = createRefFor("phoneField")
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

                    constrain(refTabRowField) {
                        top.linkTo(refEnterTitle.bottom, 35.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refEmailField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refCountriesDropdownMenu) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refCountriesCodeField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(refCountriesDropdownMenu.end, 8.dp)
                    }

                    constrain(refPhoneField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(refCountriesCodeField.end, 36.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPasswordField) {
                        top.linkTo(refTabRowField.bottom, 100.dp)
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
                modifier = Modifier
                    .fillMaxSize()
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

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier
                        .layoutId("tabRowField")
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    contentColor = LightBlue,
                ) {
                    listOf(
                        stringResource(R.string.email),
                        stringResource(R.string.phone)
                    ).forEachIndexed { index, text ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = {
                                when(index) {
                                    0 -> viewModel.updateLoginViewState(passwordVerificationType = PasswordVerificationType.EMailVerification)
                                    1 -> viewModel.updateLoginViewState(passwordVerificationType = PasswordVerificationType.PhoneVerification)
                                }
                                selectedTabIndex = index
                            },
                            modifier = Modifier
                                .background(DarkBlue)
                                .height(50.dp),
                            text = {
                                Text(text = text)
                            },
                            selectedContentColor = LightBlue,
                            unselectedContentColor = White
                        )
                    }
                }

                var emailText by remember { mutableStateOf(TextFieldValue("")) }
                var phoneText by remember { mutableStateOf(TextFieldValue("")) }

                when (selectedTabIndex) {
                    0 -> {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .layoutId("emailField"),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = DarkBlue
                            ),
                            value = emailText,
                            onValueChange = {
                                emailText = it
                                viewModel.updateLoginViewState(email = it.text)
                            },
                            label = {
                                Text(
                                    text = if (state.emailErrors.isNotEmpty()) state.emailErrors.first() else stringResource(
                                        id = R.string.email
                                    ),
                                    fontSize = 10.sp,
                                    color = if (emailText.text.isEmpty())
                                        DarkGray
                                    else if (state.emailErrors.isNotEmpty())
                                        Red
                                    else
                                        White
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.email),
                                    fontSize = 14.sp,
                                    color = White
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                    }
                    1 -> {
                        var expanded by remember { mutableStateOf(false) }
                        val items = getCountriesList()
                        var selectedIndex by remember {
                            mutableStateOf(
                                items.indexOf(
                                    defaultCountryData
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .layoutId("countriesDropdownMenu")
                        ) {
                            Text(
                                items[selectedIndex].flag,
                                modifier = Modifier
                                    .padding(top = 26.dp)
                                    .width(40.dp)
                                    .clickable(onClick = { expanded = true }),
                                fontSize = 16.sp
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                DropdownMenuItem(onClick = {
                                    selectedIndex = items.indexOf(defaultCountryData)
                                    expanded = false
                                }) {
                                    Text(
                                        text = defaultCountryData.codeAndFlag,
                                    )
                                }

                                Divider()

                                items.forEachIndexed { index, s ->
                                    DropdownMenuItem(onClick = {
                                        selectedIndex = index
                                        expanded = false
                                    }) {
                                        Text(
                                            text = s.codeAndFlag,
                                        )
                                    }
                                }
                            }
                        }

                        Text(
                            text = items[selectedIndex].code,
                            modifier = Modifier
                                .padding(top = 26.dp)
                                .layoutId("countriesCodeField")
                                .width(120.dp)
                        )

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .layoutId("phoneField"),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = DarkBlue
                            ),
                            value = phoneText,
                            onValueChange = {
                                viewModel.updateLoginViewState(phone = it.text)
                                phoneText = it
                            },
                            label = {
                                Text(
                                    text = if (state.phoneErrors.isNotEmpty()) state.phoneErrors.first() else stringResource(
                                        id = R.string.phone
                                    ),
                                    fontSize = 10.sp,
                                    color = if (phoneText.text.isEmpty())
                                        DarkGray
                                    else if (state.phoneErrors.isNotEmpty())
                                        Red
                                    else
                                        White
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.phone),
                                    fontSize = 14.sp,
                                    color = White
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )
                    }
                }

                var password by rememberSaveable { mutableStateOf("") }
                var passwordVisibility by remember { mutableStateOf(false) }

                TextField(
                    modifier = Modifier
                        .layoutId("passwordField")
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = DarkBlue
                    ),
                    value = password,
                    onValueChange = {
                        password = it
                        viewModel.onPasswordChanged(it)
                        viewModel.updateLoginViewState(password = it)
                    },
                    label = {
                        Text(
                            text = if (state.passwordErrors.isNotEmpty()) state.passwordErrors.first() else stringResource(
                                id = R.string.enter_password
                            ),
                            fontSize = 10.sp,
                            color =
                            if (password.isEmpty())
                                DarkGray
                            else if (state.passwordErrors.isNotEmpty())
                                Red
                            else
                                White
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
                when (state.isFieldsCorrect) {
                    true -> {
                        CyberButton(
                            title = stringResource(R.string.enter_text_2),
                            titleSize = 20.sp,
                            onClick = { viewModel.login() },
                            Modifier
                                .layoutId("enterButton")
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .height(44.dp)
                        )
                    }
                    else -> {
                        CyberButtonWithBorder(
                            title = stringResource(R.string.enter_text_2),
                            Modifier
                                .layoutId("enterButton")
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .height(44.dp)
                        )
                    }
                }

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

                if (state.verificationCodeRequest) {
                    onRegisterClickListener()
                } else if (state.isAuthorised) {
                    goToProfileScreen()
                }

                RegisterWithSocialNetworkScreen(labelResourceId = R.string.or_join_by_text)
            }
        },
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AuthorizationScreenPreview() {
    LoginScreen({}, {}, {})
}