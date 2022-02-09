@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterialApi::class
)

package ru.cybercasino.android.system.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.cybercasino.feature.auth.LoginScreen
import ru.cybercasino.feature.auth.ui.auth.RegistrationFinishScreen
import ru.cybercasino.feature.auth.ui.auth.RegistrationScreen
import ru.cybercasino.feature.auth.ui.auth.RootScreen
import ru.cybercasino.feature.auth.ui.auth.VerificationScreen
import ru.cybercasino.ui.Dark

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

    ModalBottomSheetLayout(
        sheetContent = bottomSheetNavigator.sheetContent
    ) {
        AnimatedNavHost(
            navController,
            startDestination = TABS_GRAPH_NAV_ROUTE
        ) {
            composable(TABS_GRAPH_NAV_ROUTE) {
                RootScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Dark),
                )
                rememberCoroutineScope().launch {
                    delay(START_DELAY_TIMEOUT)
                    navController.navigate(Screen.Login.route)
                }
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    onRegisterClickListener = {
                        navController.navigate(Screen.Registration.route)
                    }
                )
            }
            composable(Screen.Registration.route) {
                RegistrationScreen(
                    onEnterClickListener = {
                        navController.navigate(Screen.Login.route)
                    },
                    onRegisterClickListener = {
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
                        navController.navigate(Screen.ProfileScreen.route)
                    }
                )
            }
            composable(Screen.RegistrationFinish.route) {
                RegistrationFinishScreen(
                    onEnterClickListener = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.ProfileScreen.route) {
                UserProfileScreen(
                    onEnterClickListener = {


                    }
                )
            }
        }
    }
}

private const val TABS_GRAPH_NAV_ROUTE = "root"
private const val START_DELAY_TIMEOUT = 1000L