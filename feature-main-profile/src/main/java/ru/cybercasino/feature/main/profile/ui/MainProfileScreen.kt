@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
)

package ru.cybercasino.feature.main.profile.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.*

@Composable
fun MainProfileScreen(
    navController: NavController,
    onEnterClickListener: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scrollState = rememberScrollState()
    val viewModel = getViewModel<MainProfileViewModel>()
    val state by viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBarMainProfile(
                barLabelTextId = 0,
                isHasNewNotification = state.isHasNewNotification,
                onMenuButtonClickListener = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onUserProfileButtonClickListener = onEnterClickListener,
            )
        },
        drawerBackgroundColor = DarkBlue,
        drawerContent = {
            MainMenuDrawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        },
        modifier = Modifier
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
            .fillMaxSize(),
        bottomBar = {
            AppBottomBar()
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val calendar = Calendar.getInstance()
                items(8) { itemIndex ->
                    when (itemIndex) {
                        0 -> ProfileTopScreen()
                        1 -> {
                            ListDivider(
                                dividerLabel = stringResource(id = R.string.news_games_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
                            )

                            NewGamesScreen(
                                imagesList = listOf(
                                    R.drawable.kozino_background_1,
                                    R.drawable.kozino_background_2,
                                    R.drawable.kozino_background_3,
                                    R.drawable.kozino_background_4
                                )
                            )
                        }
                        2 -> {
                            ListDivider(
                                dividerLabel = stringResource(id = R.string.last_winners_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
                            )

                            LastWinnersScreen()
                        }
                        3 -> {

                            ListDivider(
                                dividerLabel = stringResource(id = R.string.jackpot_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
                            )

                            JackpotScreen()
                        }
                        4 -> {

                            ListDivider(
                                dividerLabel = stringResource(id = R.string.news_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
                            )

                            NewsItemScreen(
                                dateAndTime = calendar.time,
                                newsHeader = "?????????????? ?????????? $itemIndex",
                                newsContent = "?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ?????????????? ?????????? $itemIndex ",
                            )

                            CyberButton(
                                title = stringResource(id = R.string.show_more),
                                titleSize = 16.sp,
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .padding(start = 28.dp, end = 28.dp)
                            )
                        }
                        5 -> {
                            ListDivider(
                                dividerLabel = stringResource(id = R.string.payment_systems_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
                            )
                            PaymentSystemsScreen(
                                listPaymentSystems = listOfMainPaymentSystems,
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp),
                            )
                        }
                        6 -> {

                            ListDivider(
                                dividerLabel = stringResource(id = R.string.payment_systems_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
                            )

                            PaymentSystemsScreen(
                                listPaymentSystems = listOfAdditionalPaymentSystems,
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp),
                            )
                        }
                        7 -> {

                            BottomDivider()

                            ProfileBottomScreen()
                        }
                    }
                }
            }
        }
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MainProfileScreenPreview() {
    val navController = rememberNavController()
    MainProfileScreen(navController, {})
}