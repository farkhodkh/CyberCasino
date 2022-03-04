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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.*

@Composable
fun MainProfileScreen(
    onEnterClickListener: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val viewModel = getViewModel<MainProfileViewModel>()
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
                items(12) { itemIndex ->
                    when (itemIndex) {
                        0 -> ProfileTopScreen()
                        11 -> {
                            CyberButton(
                                title = stringResource(id = R.string.show_more),
                                titleSize = 16.sp,
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .padding(start = 28.dp, end = 28.dp)
                            )

                            BottomDivider()

                            ProfileBottomScreen()
                        }
                        in 2..4 -> {

                            ListDivider(
                                dividerLabel = stringResource(id = R.string.news_games_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp),
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
                        else -> {

                            ListDivider(
                                dividerLabel = stringResource(id = R.string.news_label),
                                modifier = Modifier
                                    .padding(start = 28.dp, end = 28.dp),
                            )

                            NewsItemScreen(
                                dateAndTime = calendar.time,
                                newsHeader = "Новость номер $itemIndex",
                                newsContent = "Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex Новость номер $itemIndex ",
                            )
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
    MainProfileScreen({})
}