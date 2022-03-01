@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterialApi::class
)

package ru.cybercasino.android.system.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import ru.cybercasino.feature.auth.ui.LoginScreen
import ru.cybercasino.feature.auth.ui.RegistrationScreen
import ru.cybercasino.feature.auth.ui.VerificationScreen
import ru.cybercasino.feature.main.profile.ui.ChooseLanguageScreen
import ru.cybercasino.feature.main.profile.ui.MainProfileScreen

/**
 * The application's navigation graph.
 * Contains 2 navigation controllers:
 * 1. Controller for the fullscreen destinations.
 * 2. Controller for the tab destinations.
 *
 * @param modifier The modifier for root composable.
 */
@Composable
@Suppress("LongMethod")
fun AppNavGraph(modifier: Modifier) {
    val navController = rememberAnimatedNavController()

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val bottomSheetNavigator = remember(sheetState) { BottomSheetNavigator(sheetState) }
    navController.navigatorProvider += bottomSheetNavigator

    bottomSheetNavigator.navigatorSheetState.currentValue
    val focusManager = LocalFocusManager.current

    ModalBottomSheetLayout(
        sheetContent = bottomSheetNavigator.sheetContent
    ) {
        AnimatedNavHost(
            navController,
            startDestination = TABS_GRAPH_NAV_ROUTE
        ) {
            composable(TABS_GRAPH_NAV_ROUTE) {
//                RootScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Dark),
//                )
//                rememberCoroutineScope().launch {
//                    delay(1000)
                    navController.navigate(Screen.Login.route)
//                }
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onClickListener = {navController.navigate(Screen.Registration.route)},
                    onRegisterClickListener = {
                        navController.navigate(Screen.VerificationScreen.route)
                    },
                    goToMainProfileScreen = {
                        navController.navigate(Screen.MainProfileScreen.route)
                    }
                )
            }
            composable(Screen.Registration.route) {
                RegistrationScreen(
                    onEnterClickListener = {
                        navController.navigate(Screen.Login.route)
                    },
                    onVerificationCodeRequest = {
                        navController.navigate(Screen.VerificationScreen.route)
                    }
                )
            }
            composable(Screen.VerificationScreen.route) {
                VerificationScreen(
                    onEnterClickListener = {
                        navController.navigate(Screen.Login.route)
                    },
                    goToProfileScreen = {
                        navController.navigate(Screen.MainProfileScreen.route)
                    }
                )
            }
            composable(Screen.MainProfileScreen.route) {
                MainProfileScreen(
                    onEnterClickListener = {
                        navController.navigate(Screen.ChooseLanguageScreen.route)
                    }
                )
            }
            composable(Screen.ChooseLanguageScreen.route) {
                ChooseLanguageScreen(
                    onChooseLanguage = {
                        navController.navigate(Screen.MainProfileScreen.route)
                    }
                )
            }
        }
    }
}

private const val TABS_GRAPH_NAV_ROUTE = "root"