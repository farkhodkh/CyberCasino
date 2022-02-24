@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
)

package ru.cybercasino.feature.main.profile.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBarUserProfile
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.elements.ListDivider

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
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val calendar = Calendar.getInstance()
                items(10) { itemIndex ->
                    when (itemIndex) {
                        0 -> ProfileTopScreen()
                        else -> {
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

@Composable
private fun ProfileTopScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(id = R.drawable.background_main_play),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            ProfileTitle(
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 16.dp)
            )

            CyberButton(
                title = stringResource(id = R.string.play_label),
                titleSize = 16.sp,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .padding(start = 28.dp, end = 28.dp)
            )
        }

        ListDivider(
            dividerLabel = stringResource(id = R.string.news_label),
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun ProfileTitle(
    modifier: Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                )
            ) {
                append(stringResource(id = R.string.win_and_have_fun_with_cybercasino))
            }
            withStyle(
                style = SpanStyle(
                    color = Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            ) {
                append("\n" + stringResource(id = R.string.cybercasino))
            }
        },
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .then(modifier),
        fontSize = 32.sp,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MainProfileScreenPreview() {
    MainProfileScreen({})
}