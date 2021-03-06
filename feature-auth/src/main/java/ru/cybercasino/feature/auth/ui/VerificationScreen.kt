@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.feature.auth.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import org.koin.androidx.compose.getStateViewModel
import ru.cybercasino.feature.auth.ui.auth.RegisterWithSocialNetworkScreen
import ru.cybercasino.feature.auth.viewmodel.AuthorizationViewModel
import ru.cybercasino.feature.auth.viewmodel.AuthentificationType
import ru.cybercasino.ui.BlueGrey
import ru.cybercasino.ui.LightBlue
import ru.cybercasino.ui.R
import ru.cybercasino.ui.Red
import ru.cybercasino.ui.elements.AppTopAppBarRegistration
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.elements.CyberButtonWithBorder
import ru.cybercasino.ui.elements.RegistrationCodeInputScreen

@Composable
fun VerificationScreen(
    onEnterClickListener: () -> Unit,
    goToProfileScreen: () -> Unit
) {
    val viewModel = getStateViewModel<AuthorizationViewModel>()
    val state by viewModel.state.collectAsState()
    val resendTimeout by viewModel.resendTimeout.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBarRegistration(
                buttonLabelTextId = R.string.enter_text,
                onButtonClickListener = onEnterClickListener
            )
        },
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refVerificationTitle = createRefFor("verificationTitle")
                    val refPasswordVerificationType = createRefFor("passwordVerificationType")
                    val refUserLoginLabel = createRefFor("userLoginLabel")
                    val refVerificationCodeErrorField = createRefFor("verificationCodeErrorField")
                    val refVerificationCodeField = createRefFor("verificationCodeField")
                    val refEnterButton = createRefFor("enterButton")
                    val refResendVerificationButton = createRefFor("resendVerificationButton")
                    val refResendVerificationCodeLabel = createRefFor("resendVerificationCodeLabel")
                    val refVerificationCodeTimeoutLabel =
                        createRefFor("verificationCodeTimeoutLabel")
                    val refJoinWithSocialNetworks = createRefFor("joinWithSocialNetworks")

                    constrain(refVerificationTitle) {
                        top.linkTo(parent.top, 50.dp)
                        start.linkTo(parent.start, 16.dp)
                    }

                    constrain(refPasswordVerificationType) {
                        top.linkTo(refVerificationTitle.bottom, 46.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refUserLoginLabel) {
                        top.linkTo(refPasswordVerificationType.bottom, 7.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refVerificationCodeErrorField) {
                        top.linkTo(refUserLoginLabel.bottom, 45.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refVerificationCodeField) {
                        top.linkTo(refUserLoginLabel.bottom, 58.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refEnterButton) {
                        top.linkTo(refVerificationCodeField.bottom, 56.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refResendVerificationButton) {
                        top.linkTo(refEnterButton.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refResendVerificationCodeLabel) {
                        top.linkTo(refEnterButton.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refVerificationCodeTimeoutLabel) {
                        top.linkTo(refEnterButton.bottom, 16.dp)
                        start.linkTo(refResendVerificationCodeLabel.end, 4.dp)
                    }

                    constrain(refJoinWithSocialNetworks) {
                        top.linkTo(refVerificationCodeTimeoutLabel.bottom, 26.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end)
                    }
                },
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .layoutId("verificationTitle"),
                    text = stringResource(id = R.string.verification_text),
                    fontSize = 32.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                )

                val verification = when (state.authentificationType) {
                    AuthentificationType.EMail -> {
                        Pair(
                            stringResource(id = R.string.verification_code_sended_to_email_text),
                            state.email ?: ""
                        )
                    }
                    AuthentificationType.Phone -> {
                        Pair(
                            stringResource(id = R.string.verification_code_sended_to_phone_text),
                            state.phone ?: ""
                        )
                    }
                }

                Text(
                    modifier = Modifier
                        .layoutId("passwordVerificationType"),
                    text = verification.first,
                    fontSize = 12.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = BlueGrey
                    )
                )

                Text(
                    modifier = Modifier
                        .layoutId("userLoginLabel"),
                    text = verification.second,
                    fontSize = 14.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                )

                Text(
                    text = state.verificationCodeError,
                    modifier = Modifier
                        .layoutId("verificationCodeErrorField"),
                    color = Red
                )

                RegistrationCodeInputScreen(
                    onCodeEnter = { verificationCode ->
                        viewModel.updateViewState(verificationCode = verificationCode)
                    },
                    modifier = Modifier
                        .layoutId("verificationCodeField"),
                )

                when (state.verificationCode) {
                    "" -> {
                        CyberButtonWithBorder(
                            title = stringResource(id = R.string.confirm),
                            modifier = Modifier
                                .layoutId("enterButton")
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .height(44.dp)
                        )
                    }
                    else -> {
                        CyberButton(
                            title = stringResource(id = R.string.confirm),
                            titleSize = 16.sp,
                            onClick = {
                                viewModel.checkCode()
                            },
                            modifier = Modifier
                                .layoutId("enterButton")
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .height(44.dp)
                        )
                    }
                }

                when (resendTimeout) {
                    "" -> {
                        CyberButton(
                            title = stringResource(id = R.string.retry),
                            titleSize = 16.sp,
                            onClick = {
                                viewModel.startVerificationCodeRequestTimer()
                                viewModel.sendCode()
                            },
                            modifier = Modifier
                                .layoutId("resendVerificationButton")
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .height(44.dp)
                        )
                    }
                    else -> {
                        Text(
                            modifier = Modifier
                                .layoutId("resendVerificationCodeLabel"),
                            text = stringResource(id = R.string.resend_after_text),
                            fontSize = 12.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                        )

                        Text(
                            modifier = Modifier
                                .layoutId("verificationCodeTimeoutLabel"),
                            text = resendTimeout,
                            fontSize = 12.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                color = LightBlue
                            )
                        )
                    }
                }

                if (state.isAuthorised) {
                    goToProfileScreen()
                }

                RegisterWithSocialNetworkScreen(labelResourceId = R.string.or_register_by_text)
            }
        }
    )

    if (state.verificationCodeRequested) {
        viewModel.startVerificationCodeRequestTimer()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun VerificationScreenPreview() {
    VerificationScreen({}, {})
}