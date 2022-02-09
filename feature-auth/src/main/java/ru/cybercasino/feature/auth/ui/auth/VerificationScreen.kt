package ru.cybercasino.feature.auth.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.feature.auth.viewmodel.PasswordVerificationType
import ru.cybercasino.ui.BlueGrey
import ru.cybercasino.ui.LightBlue
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBar
import ru.cybercasino.ui.elements.PasswordVerificationScreen

@Composable
fun VerificationScreen(
    onEnterClickListener: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()
    val resendTimeout by viewModel.resendTimeout.collectAsState()

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
                    val refVerificationTitle = createRefFor("verificationTitle")
                    val refPasswordVerificationType = createRefFor("passwordVerificationType")
                    val refUserLoginLabel = createRefFor("userLoginLabel")
                    val refVerificationCodeField = createRefFor("verificationCodeField")
                    val refResendVerificationCodeLabel = createRefFor("resendVerificationCodeLabel")
                    val refVerificationCodeTimeoutLabel = createRefFor("verificationCodeTimeoutLabel")
                    val refJoinWithSocialNetworks = createRefFor("joinWithSocialNetworks")
                    val refFacebookIcon = createRefFor("facebookIcon")
                    val refTgIcon = createRefFor("tgIcon")
                    val refGoogleIcon = createRefFor("googleIcon")

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

                    constrain(refVerificationCodeField) {
                        top.linkTo(refUserLoginLabel.bottom, 58.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refResendVerificationCodeLabel) {
                        top.linkTo(refVerificationCodeField.bottom, 131.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }

                    constrain(refVerificationCodeTimeoutLabel) {
                        top.linkTo(refVerificationCodeField.bottom, 131.dp)
                        start.linkTo(refResendVerificationCodeLabel.end, 4.dp)
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
                        .layoutId("verificationTitle"),
                    text = stringResource(id = R.string.verification_text),
                    fontSize = 32.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                )

                val verificationTypeText = when (state.passwordVerificationType) {
                    PasswordVerificationType.EMailVerification -> {
                        stringResource(id = R.string.verification_code_sended_to_email_text)
                    }
                    PasswordVerificationType.PhoneVerification -> {
                        stringResource(id = R.string.verification_code_sended_to_phone_text)
                    }
                }

                Text(
                    modifier = Modifier
                        .layoutId("passwordVerificationType"),
                    text = verificationTypeText,
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
                    text = state.userLogin,
                    fontSize = 14.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                )

                PasswordVerificationScreen(
                    modifier = Modifier
                        .layoutId("verificationCodeField"),
                )

                when(resendTimeout) {
                    "" -> {
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

                RegisterWithSocialNetworkScreen(labelResourceId = R.string.or_register_by_text)
            }
        }
    )
}