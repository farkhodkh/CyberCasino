@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
)

package ru.cybercasino.feature.main.profile.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import ru.cybercasino.feature.main.profile.domain.DrawerScreen
import ru.cybercasino.feature.main.profile.domain.NavDrawerItem
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.CyberButton

@Composable
fun MainMenuDrawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    val topItems = listOf(
        NavDrawerItem.RealAccount,
        NavDrawerItem.BonusAccount,
        NavDrawerItem.ExperienceGrade,
        NavDrawerItem.FreeSpins,
    )

    val bottomItems = listOf(
        NavDrawerItem.UserProfile,
        NavDrawerItem.Account,
        NavDrawerItem.Deposit,
        NavDrawerItem.FundsWithdraw,
        NavDrawerItem.MoneyTransferHistory,
        NavDrawerItem.YourBids,
        NavDrawerItem.BidsHistory,
        NavDrawerItem.Documents,
        NavDrawerItem.Messages,
        NavDrawerItem.Tournaments,
    )

    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .verticalScroll(state = scrollState)
            .background(DarkBlue)
    ) {
        // Header
        DrawerHeader(
            { navController.navigate(DrawerScreen.Profile.route) }
        )

        Divider(thickness = 1.dp, color = DarkGray)

        // Space between
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        // List of navigation items
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        topItems.forEach { item ->
            DrawerTopItem(
                item = item,
                selected = currentRoute == item.route,
                onItemClick = { /* Add code later */ }
            )
        }

        CyberButton(
            title = stringResource(id = R.string.credit_balance_label),
            titleSize = 16.sp,
            textColor = White,
            onClick = {},
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 18.dp)
                .fillMaxWidth()
                .height(38.dp)
        )

        Divider(thickness = 1.dp, color = DarkGray)

        bottomItems.forEach { item ->
            DrawerBottomItem(
                item = item,
                selected = currentRoute == item.route,
                onItemClick = { /* Add code later */ }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MainMenuDrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()

    MainMenuDrawer(scope, scaffoldState, navController)
}