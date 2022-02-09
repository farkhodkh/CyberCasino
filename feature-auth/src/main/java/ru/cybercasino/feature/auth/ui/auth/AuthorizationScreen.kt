@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.auth.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.TabRow
import androidx.compose.material.Tab
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import ru.cybercasino.feature.auth.ui.auth.RegisterWithSocialNetworkScreen
import ru.cybercasino.ui.LightBlue
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.White
import ru.cybercasino.ui.DarkGray
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBar
import ru.cybercasino.ui.elements.CyberButtonWithBorder
import ru.cybercasino.ui.utils.defaultCountryData
import ru.cybercasino.ui.utils.getCountriesList

/**
 * Authorization screen
 * @param onClickListener on registration button click listener
 */
@Composable
fun AuthorizationScreen(
    onClickListener: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    //val scope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBar(
                buttonLabelTextId = R.string.registration_text,
                onButtonClickListener = onClickListener
            )
        },
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refEnterTitle = createRefFor("enterTitle")
                    val refTabRowField = createRefFor("tabRowField")
                    val refEmailField = createRefFor("emailField")
                    val refPhoneField = createRefFor("phoneField")
                    val refCountriesDropdownMenu = createRefFor("countriesDropdownMenu")
                    val refCountryCode = createRefFor("countryCode")
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
                        top.linkTo(refEnterTitle.bottom, 50.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refEmailField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refCountriesDropdownMenu) {
                        top.linkTo(refTabRowField.bottom, 36.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refCountryCode) {
                        top.linkTo(refTabRowField.bottom, 50.dp)
                        start.linkTo(refCountriesDropdownMenu.end, 2.dp)
                    }

                    constrain(refPhoneField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(refCountryCode.end, 46.dp)
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
                            onClick = { selectedTabIndex = index },
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
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.email),
                                    fontSize = 10.sp,
                                    color = if (emailText.text.isEmpty()) DarkGray else White
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.email),
                                    fontSize = 14.sp,
                                    color = White
                                )
                            },
                        )
                    }
                    1 -> {

                        var expanded by remember { mutableStateOf(false) }
                        val countriesList = getCountriesList()
                        val selectedItem = remember { mutableStateOf(defaultCountryData) }

                        Box(
                            modifier = Modifier.layoutId("countriesDropdownMenu"),
                        ) {
                            IconButton(onClick = { expanded = true }) {
                                Row {
                                    Text( text = selectedItem.value.flag )
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_chevron_down),
                                        contentDescription = ""
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                DropdownMenuItem(onClick = {
                                    selectedItem.value = defaultCountryData
                                    expanded = false
                                }) {
                                    Text(defaultCountryData.codeAndFlag)
                                }
                                Divider()

                                countriesList.forEach { countryData ->
                                    DropdownMenuItem(onClick = {
                                        selectedItem.value = countryData
                                        expanded = false
                                    }) {
                                        Text(countryData.codeAndFlag)
                                    }
                                }
                            }
                        }
                        Text(
                            modifier = Modifier
                                .requiredWidth(90.dp)
                                .layoutId("countryCode"),
                            fontSize = 14.sp,
                            text = selectedItem.value.code,
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
                                phoneText = it
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.phone),
                                    fontSize = 10.sp,
                                    color = if (phoneText.text.isEmpty()) DarkGray else White
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.phone),
                                    fontSize = 14.sp,
                                    color = White
                                )
                            },
                            textStyle = TextStyle(
                                fontSize = 14.sp
                            )
                        )
                    }
                }

                //var emailPhoneText by remember { mutableStateOf(TextFieldValue("")) }

//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, end = 16.dp)
//                        .layoutId("loginField"),
//                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = DarkBlue
//                    ),
//                    value = emailPhoneText,
//                    onValueChange = {
//                        emailPhoneText = it
//                    },
//                    label = {
//                        Text(
//                            text = stringResource(id = R.string.enter_email_or_phone),
//                            fontSize = 10.sp,
//                            color = if (emailPhoneText.text.isEmpty()) DarkGray else White
//                        )
//                    },
//                    placeholder = {
//                        Text(
//                            text = stringResource(id = R.string.enter_email_or_phone),
//                            fontSize = 14.sp,
//                            color = White
//                        )
//                    },
//                )

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
                    onValueChange = { password = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.enter_password),
                            fontSize = 10.sp,
                            color = if (password.isEmpty()) DarkGray else White
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
                    title = stringResource(R.string.enter_text_2),
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

                RegisterWithSocialNetworkScreen(labelResourceId = R.string.or_join_by_text)
            }
        },
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AuthorizationScreenPreview() {
    AuthorizationScreen({})
}