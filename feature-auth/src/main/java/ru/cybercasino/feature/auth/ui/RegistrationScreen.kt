@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalPagerApi::class
)

package ru.cybercasino.feature.auth.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.ui.auth.RegisterWithSocialNetworkScreen
import ru.cybercasino.feature.auth.viewmodel.AuthorizationViewModel
import ru.cybercasino.feature.auth.viewmodel.AuthentificationType
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBarRegistration
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.elements.CyberButtonWithBorder
import ru.cybercasino.ui.elements.SimpleCheckboxComponent
import ru.cybercasino.ui.utils.defaultCountryData
import ru.cybercasino.ui.utils.getCountriesList

@Composable
fun RegistrationScreen(
    onEnterClickListener: () -> Unit,
    onVerificationCodeRequest: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    var selectedTabIndex by remember { mutableStateOf(0) }

    val viewModel = getViewModel<AuthorizationViewModel>()
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    var emailText by remember { mutableStateOf(TextFieldValue("")) }
    var phoneText by remember { mutableStateOf(TextFieldValue("")) }
    val countriesCodeList = getCountriesList()
    var selectedCountryItemIndex by remember {
        mutableStateOf(
            countriesCodeList.indexOf(
                defaultCountryData
            )
        )
    }
    var password by rememberSaveable { mutableStateOf("") }
    var promoCodeText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBarRegistration(
                buttonLabelTextId = R.string.enter_text,
                onButtonClickListener = {
                    onEnterClickListener()
                    viewModel.setDafaultState()
                }
            )
        },
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refRegistrationTitle = createRefFor("registrationTitle")
                    val refTabRowField = createRefFor("tabRowField")
                    val refEmailField = createRefFor("emailField")
                    val refEmailErrorField = createRefFor("emailErrorField")
                    val refCountriesDropdownMenu = createRefFor("countriesDropdownMenu")
                    val refPhoneCodeChevronField = createRefFor("phoneCodeChevronField")
                    val refCountriesCodeField = createRefFor("countriesCodeField")
                    val refPhoneField = createRefFor("phoneField")
                    val refPhoneErrorField = createRefFor("phoneErrorField")
                    val refPasswordField = createRefFor("passwordField")
                    val refPasswordErrorField = createRefFor("passwordErrorField")
                    val refPromoField = createRefFor("promoField")
                    val refPrivacyPolicy = createRefFor("privacyPolicyChB")
                    val refNewsAndOffersChB = createRefFor("newsAndOffersChB")
                    val refRegisterButton = createRefFor("registerButton")
                    val refJoinWithSocialNetworks = createRefFor("joinWithSocialNetworks")
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

                    constrain(refEmailErrorField) {
                        top.linkTo(refEmailField.bottom, 4.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refCountriesDropdownMenu) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refPhoneCodeChevronField) {
                        top.linkTo(refCountriesDropdownMenu.top)
                        start.linkTo(refTabRowField.start, 22.dp)
                    }

                    constrain(refCountriesCodeField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(refPhoneCodeChevronField.end, 4.dp)
                    }

                    constrain(refPhoneField) {
                        top.linkTo(refTabRowField.bottom, 26.dp)
                        start.linkTo(refCountriesCodeField.end, 4.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPhoneErrorField) {
                        top.linkTo(refPhoneField.bottom, 4.dp)
                        start.linkTo(refPhoneField.start)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPasswordField) {
                        top.linkTo(refTabRowField.bottom, 110.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refPasswordErrorField) {
                        top.linkTo(refPasswordField.bottom, 4.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refPromoField) {
                        top.linkTo(refPasswordField.bottom, 56.dp)
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
                        top.linkTo(refRegisterButton.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end)
                    }
                },
                modifier = Modifier
                    .verticalScroll(scrollState)
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
                            onClick = {
                                when (index) {
                                    0 -> viewModel.updateViewState(authentificationType = AuthentificationType.EMail)
                                    1 -> viewModel.updateViewState(authentificationType = AuthentificationType.Phone)
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

                when (selectedTabIndex) {
                    0 -> {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .layoutId("emailField"),
                            colors = TextFieldDefaults.textFieldColors(
                                unfocusedIndicatorColor = if (emailText.text.isEmpty())
                                    Gray
                                else if (state.emailErrors.isNotEmpty())
                                    Red
                                else Blue,
                                focusedIndicatorColor = LightBlue,
                                backgroundColor = DarkBlue
                            ),
                            value = emailText,
                            onValueChange = {
                                emailText = it
                                viewModel.updateViewState(email = it.text)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.email),
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
                                    text = stringResource(id = R.string.your_email),
                                    fontSize = 10.sp,
                                    color = White
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                        if (state.emailErrors.isNotEmpty()) {
                            Text(
                                text = state.emailErrors,
                                modifier = Modifier.layoutId("emailErrorField"),
                                color = Red
                            )
                        }
                    }
                    1 -> {
                        var expanded by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .layoutId("countriesDropdownMenu")
                        ) {
                            Text(
                                countriesCodeList[selectedCountryItemIndex].flag,
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
                                DropdownMenuItem(
                                    onClick = {
                                        selectedCountryItemIndex =
                                            countriesCodeList.indexOf(defaultCountryData)
                                        viewModel.updateViewState(selectedCountry = countriesCodeList[selectedCountryItemIndex])
                                        expanded = false
                                    })
                                {
                                    Text(
                                        text = defaultCountryData.codeAndFlag,
                                    )
                                }

                                Divider()

                                countriesCodeList.forEachIndexed { index, s ->
                                    DropdownMenuItem(onClick = {
                                        selectedCountryItemIndex = index
                                        viewModel.updateViewState(selectedCountry = countriesCodeList[selectedCountryItemIndex])
                                        expanded = false
                                    }) {
                                        Text(
                                            text = s.codeAndFlag,
                                        )
                                    }
                                }
                            }
                        }

                        IconButton(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .layoutId("phoneCodeChevronField"),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_chevron_down),
                                contentDescription = ""
                            )
                        }

                        Text(
                            text = countriesCodeList[selectedCountryItemIndex].code,
                            modifier = Modifier
                                .padding(top = 26.dp)
                                .layoutId("countriesCodeField")
                                .width(140.dp)
                        )

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.Start)
                                .padding(start = 16.dp, end = 16.dp)
                                .layoutId("phoneField"),
                            colors = TextFieldDefaults.textFieldColors(
                                unfocusedIndicatorColor = if (phoneText.text.isEmpty())
                                    Gray
                                else if (state.phoneErrors.isNotEmpty())
                                    Red
                                else Blue,
                                focusedIndicatorColor = LightBlue,
                                backgroundColor = DarkBlue
                            ),
                            value = phoneText,
                            onValueChange = {
                                viewModel.updateViewState(phone = it.text)
                                phoneText = it
                            },
                            label = {
                                Text(
                                    text = stringResource(
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
                                    fontSize = 10.sp,
                                    color = White
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )
                        if (state.phoneErrors.isNotEmpty()) {
                            Text(
                                text = state.phoneErrors,
                                color = Red,
                                modifier = Modifier.layoutId("phoneErrorField")
                            )
                        }
                    }
                }

                var passwordVisibility by remember { mutableStateOf(false) }

                TextField(
                    modifier = Modifier
                        .layoutId("passwordField")
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = if (password.isEmpty())
                            Gray
                        else if (state.passwordErrors.isNotEmpty())
                            Red
                        else Blue,
                        focusedIndicatorColor = LightBlue,
                        backgroundColor = DarkBlue
                    ),
                    value = password,
                    onValueChange = {
                        password = it
                        viewModel.onPasswordChanged(it)
                        viewModel.updateViewState(password = it)
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.enter_password),
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
                            fontSize = 10.sp,
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
                    text = if (state.passwordErrors.isNotEmpty())
                        AnnotatedString(text = state.passwordErrors)
                    else
                        getPasswordRequirementsText(state.passwordRequirementsState),
                    color = Red,
                    modifier = Modifier.layoutId("passwordErrorField")
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .layoutId("promoField"),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = if (promoCodeText.text.isEmpty())
                            Gray
                        else if (state.promoCodeText.isNotEmpty())
                            Red
                        else Blue,
                        focusedIndicatorColor = LightBlue,
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
                            fontSize = 10.sp,
                            color = White
                        )
                    },
                )

                val privacyPolicyCheckedState = mutableStateOf(false)

                SimpleCheckboxComponent(
                    modifier = Modifier.layoutId("privacyPolicyChB"),
                    titleResourceId = R.string.age_limit_label,
                    checkedStateValue = privacyPolicyCheckedState,
                    onCheckedChanged = { value ->
                        viewModel.updateViewState(
                            privacyPolicyCheckedState = value
                        )
                    }
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
                        viewModel.updateViewState()
                    }
                )

                val newsAndOffersChBCheckedState = mutableStateOf(false)

                SimpleCheckboxComponent(
                    modifier = Modifier.layoutId("newsAndOffersChB"),
                    titleResourceId = R.string.newsletter_and_offers_label,
                    checkedStateValue = newsAndOffersChBCheckedState,
                    onCheckedChanged = { value ->
                        viewModel.updateViewState(
                            newsAndOffersCheckedState = value
                        )
                    }
                )

                when (state.isFieldsCorrect) {
                    true -> CyberButton(
                        title = stringResource(R.string.registration_text_2),
                        onClick = {
                            viewModel.validateUser()
                        },
                        modifier = Modifier
                            .layoutId("registerButton")
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(44.dp),
                        titleSize = 16.sp
                    )
                    else -> CyberButtonWithBorder(
                        title = stringResource(R.string.registration_text_2),
                        Modifier
                            .layoutId("registerButton")
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(44.dp)
                    )
                }

                if (state.goToVerificationScreen) {
                    viewModel.sendCode()
                    onVerificationCodeRequest()
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
            textDecoration = TextDecoration.Underline
        )
    ) {
        append(text = stringResource(id = labelResourceId))
    }
}

@Composable
private fun getPasswordRequirementsText(someCheck: Int) = buildAnnotatedString {

    when (someCheck) {
        //Empty fields
        0 -> ""
        //Only digits
        1 -> {
            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.one_letter))
            }

            withStyle(style = SpanStyle(color = LightBlue)) {
                append("  ??? " + stringResource(id = R.string.one_number))
                append("  " + stringResource(id = R.string.minimum_symbols))
            }
        }
        //Only letters
        2 -> {
            withStyle(style = SpanStyle(color = LightBlue)) {
                append("  ??? " + stringResource(id = R.string.one_letter))
            }
            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.one_number))
            }
            withStyle(style = SpanStyle(color = LightBlue)) {
                append("  " + stringResource(id = R.string.minimum_symbols))
            }

        }
        //Noo digits and no letters
        3 -> {
            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.one_letter))
                append("  " + stringResource(id = R.string.one_number))
                append("  " + stringResource(id = R.string.minimum_symbols))
            }
        }
        //Less than 6 symbols
        4 -> {

            withStyle(style = SpanStyle(color = LightBlue)) {
                append("  ??? " + stringResource(id = R.string.one_letter))
                append("  ??? " + stringResource(id = R.string.one_number))
            }

            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.minimum_symbols))
            }
        }
        //Les than 6 symbols and digits only
        5 -> {
            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.one_letter))
            }
            withStyle(style = SpanStyle(color = LightBlue)) {
                append("  ??? " + stringResource(id = R.string.one_number))
            }
            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.minimum_symbols))
            }
        }
        //Les than 6 symbols and letters only
        6 -> {
            withStyle(style = SpanStyle(color = LightBlue)) {
                append("  ??? " + stringResource(id = R.string.one_letter))
            }
            withStyle(style = SpanStyle(color = Red)) {
                append("  " + stringResource(id = R.string.one_number))
                append("  " + stringResource(id = R.string.minimum_symbols))
            }
        }
        //Correct fields
        7 -> {
            withStyle(style = SpanStyle(color = LightBlue)) {
                append(
                    "???" + stringResource(id = R.string.one_letter) + "   ???" + stringResource(id = R.string.one_number) + "   ???" + stringResource(
                        id = R.string.minimum_symbols
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen({}, {})
}