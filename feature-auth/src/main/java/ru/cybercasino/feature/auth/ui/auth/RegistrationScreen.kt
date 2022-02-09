@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalPagerApi::class
)

package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBar
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.elements.CyberButtonWithBorder
import ru.cybercasino.ui.elements.SimpleCheckboxComponent

@Composable
fun RegistrationScreen(
    onEnterClickListener: () -> Unit,
    onRegisterClickListener: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableStateOf(0) }

    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBar(
                buttonLabelTextId = R.string.enter_text,
                onButtonClickListener = onEnterClickListener
            )
        },
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refRegistrationTitle = createRefFor("registrationTitle")
                    val refTabRowField = createRefFor("tabRowField")
                    val refEmailField = createRefFor("emailField")
                    val refCountriesDropdownMenu = createRefFor("countriesDropdownMenu")
                    val refPhoneField = createRefFor("phoneField")
                    val refPasswordField = createRefFor("passwordField")
                    val refPasswordRequirementsLabel = createRefFor("passwordRequirementsLabel")
                    val refPromoField = createRefFor("promoField")
                    val refPrivacyPolicy = createRefFor("privacyPolicyChB")
                    val refNewsAndOffersChB = createRefFor("newsAndOffersChB")
                    val refRegisterButton = createRefFor("registerButton")
                    val refJoinWithSocialNetworks = createRefFor("joinWithSocialNetworks")
                    val refFacebookIcon = createRefFor("facebookIcon")
                    val refTgIcon = createRefFor("tgIcon")
                    val refGoogleIcon = createRefFor("googleIcon")
                    val refAnd1 = createRefFor("and1")
                    val refAnd2 = createRefFor("and2")
                    val refRegulationLabel = createRefFor("regulationLabel")
                    val refTermsOfUseLabel = createRefFor("termsOfUseLabel")
                    val refPrivacyPolicyLabel = createRefFor("privacyPolicyLabel")

                    constrain(refRegistrationTitle) {
                        top.linkTo(parent.top, 50.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refTabRowField) {
                        top.linkTo(refRegistrationTitle.bottom, 35.dp)
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
                        //end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPhoneField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(refCountriesDropdownMenu.end, 206.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPasswordField) {
                        top.linkTo(refTabRowField.bottom, 100.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPasswordRequirementsLabel) {
                        top.linkTo(refPasswordField.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPromoField) {
                        top.linkTo(refPasswordField.bottom, 46.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPrivacyPolicy) {
                        top.linkTo(refPromoField.bottom, 30.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refRegulationLabel) {
                        top.linkTo(refPrivacyPolicy.bottom, 4.dp)
                        start.linkTo(refPrivacyPolicy.start, 36.dp)
                    }

                    constrain(refAnd1) {
                        top.linkTo(refPrivacyPolicy.bottom, 4.dp)
                        start.linkTo(refRegulationLabel.end, 4.dp)
                    }

                    constrain(refTermsOfUseLabel) {
                        top.linkTo(refPrivacyPolicy.bottom, 4.dp)
                        start.linkTo(refAnd1.end, 4.dp)
                    }

                    constrain(refAnd2) {
                        top.linkTo(refPrivacyPolicy.bottom, 4.dp)
                        start.linkTo(refTermsOfUseLabel.end, 4.dp)
                    }

                    constrain(refPrivacyPolicyLabel) {
                        top.linkTo(refRegulationLabel.bottom, 4.dp)
                        start.linkTo(refPrivacyPolicy.start, 36.dp)
                    }

                    constrain(refNewsAndOffersChB) {
                        top.linkTo(refPrivacyPolicyLabel.bottom, 24.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refRegisterButton) {
                        top.linkTo(refNewsAndOffersChB.bottom, 26.dp)
                        start.linkTo(parent.start, 16.dp)
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
                    .fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier
                        .layoutId("registrationTitle"),
                    text = stringResource(id = R.string.registration_text),
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
//                        var expanded by remember { mutableStateOf(true)}
//                        val cityList = listOf("Россия", "Казахстан", "Беларусь")
//
//                        DropdownMenu(
//                            modifier = Modifier
//                                .width(140.dp)
//                                .layoutId("countriesDropdownMenu"),
//                            expanded = expanded,
//                            properties = PopupProperties(focusable = true),
//                            onDismissRequest = {
//                                expanded = false
//                            }
//                        ) {
//                            cityList.forEach { city ->
//                                DropdownMenuItem(onClick = {
//                                    expanded = false
//                                }) {
//                                    Text(city)
//                                }
//                            }
//                        }

                        var expanded = remember { mutableStateOf(false) }

//                        val iconButton = @Composable {
//                            IconButton(onClick = { expanded.value = true }) {
//                                Icon(Icons.Default.MoreVert)
//                            }
//                        }

                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            //toggle = iconButton,
                            modifier = Modifier.layoutId("countriesDropdownMenu")
                        ) {
                            DropdownMenuItem(onClick = { }) {
                                Text("Share")
                            }
                            DropdownMenuItem(onClick = { }) {
                                Text("Report ")
                            }
                            DropdownMenuItem(onClick = { }) {
                                Text("Follow")
                            }
                        }

//                        TextField(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(start = 16.dp, end = 16.dp)
//                                .layoutId("phoneField"),
//                            colors = TextFieldDefaults.textFieldColors(
//                                backgroundColor = DarkBlue
//                            ),
//                            value = phoneText,
//                            onValueChange = {
//                                phoneText = it
//                            },
//                            label = {
//                                Text(
//                                    text = stringResource(id = R.string.phone),
//                                    fontSize = 10.sp,
//                                    color = if (phoneText.text.isEmpty()) DarkGray else White
//                                )
//                            },
//                            placeholder = {
//                                Text(
//                                    text = stringResource(id = R.string.phone),
//                                    fontSize = 14.sp,
//                                    color = White
//                                )
//                            },
//                        )
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
                    },
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

                Text(
                    modifier = Modifier
                        .layoutId("passwordRequirementsLabel"),
                    text = getPasswordRequirementsText(state.passwordRequirementsState),
                    fontSize = 10.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = LightBlue
                    )
                )

                var promoCodeText by remember { mutableStateOf(TextFieldValue("")) }

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .layoutId("promoField"),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = DarkBlue
                    ),
                    value = promoCodeText,
                    onValueChange = {
                        promoCodeText = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.promo_code),
                            fontSize = 10.sp,
                            color = if (promoCodeText.text.isEmpty()) DarkGray else White
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.promo_code),
                            fontSize = 14.sp,
                            color = White
                        )
                    },
                )

                SimpleCheckboxComponent(
                    modifier = Modifier.layoutId("privacyPolicyChB"),
                    titleResourceId = R.string.age_limit_label
                )

                ClickableText(
                    modifier = Modifier
                        .layoutId("regulationLabel"),
                    text = getAnnotatedText(R.string.regulation),
                    onClick = {
                        //TODO
                    }
                )

                Text(
                    modifier = Modifier
                        .layoutId("and1"),
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.and)
                )

                ClickableText(
                    modifier = Modifier
                        .layoutId("termsOfUseLabel"),
                    text = getAnnotatedText(R.string.terms_of_use),
                    onClick = {
                        //TODO
                    }
                )

                Text(
                    modifier = Modifier
                        .layoutId("and2"),
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.and)
                )

                ClickableText(
                    modifier = Modifier
                        .layoutId("privacyPolicyLabel"),
                    text = getAnnotatedText(R.string.privacy_policy),
                    onClick = {
                        //TODO
                    }
                )

                SimpleCheckboxComponent(
                    modifier = Modifier.layoutId("newsAndOffersChB"),
                    titleResourceId = R.string.newsletter_and_offers_label
                )


                when (state.isFieldsCorrect) {
                    true -> CyberButton(
                        title = stringResource(R.string.registration_text_2),
                        onClick = { onRegisterClickListener() },
                        Modifier
                            .layoutId("registerButton")
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(44.dp)
                    )
                    else -> CyberButtonWithBorder(
                        title = stringResource(R.string.registration_text_2),
                        onClick = {
                            /*TODO*/
                        },
                        Modifier
                            .layoutId("registerButton")
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(44.dp)
                    )
                }

                RegisterWithSocialNetworkScreen(labelResourceId = R.string.or_register_by_text)
            }
        },
    )
}

@Composable
private fun getAnnotatedText(labelResourceId: Int) = buildAnnotatedString {
    withStyle(
        style = SpanStyle(
            color = LightBlue,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            background = DarkBlue,
        )
    ) {
        append(text = stringResource(id = labelResourceId))
    }
}

@Composable
private fun getPasswordRequirementsText(someCheck: Int) = when (someCheck) {
    0 -> "✓ " + stringResource(id = R.string.one_letter) + "   " + stringResource(id = R.string.one_number) + "   " + stringResource(
        id = R.string.minimum_symbols
    )
    1 -> "✓ " + stringResource(id = R.string.one_letter) + "   ✓ " + stringResource(id = R.string.one_number) + "   " + stringResource(
        id = R.string.minimum_symbols
    )
    2 -> "✓ " + stringResource(id = R.string.one_letter) + "   ✓ " + stringResource(id = R.string.one_number) + "   ✓ " + stringResource(
        id = R.string.minimum_symbols
    )
    else -> "" + stringResource(id = R.string.one_letter) + "   " + stringResource(id = R.string.one_number) + "   " + stringResource(
        id = R.string.minimum_symbols
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen({}, {})
}