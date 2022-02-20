package ru.cybercasino.feature.user.profile.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.user.profile.viewmodel.UserProfileViewModel
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBarUserProfile

@Composable
fun UserProfileScreen(
    onEnterClickListener: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val viewModel = getViewModel<UserProfileViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBarUserProfile(
                barLabelTextId = R.string.enter_text,
                state.isHasNewNotification,
                onNotificationButtonClickListener = onEnterClickListener,
                onUserProfileButtonClickListener = onEnterClickListener,
            )
        },
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refVerificationTitle = createRefFor("verificationTitle")
                    val refPasswordVerificationType = createRefFor("passwordVerificationType")
                    val refUserLoginLabel = createRefFor("userLoginLabel")
                },
                modifier = Modifier
                    .fillMaxSize()
            ) {

            }
        }
    )
}