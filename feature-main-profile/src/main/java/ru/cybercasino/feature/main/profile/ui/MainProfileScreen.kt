@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
)

package ru.cybercasino.feature.main.profile.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel
import ru.cybercasino.ui.Green
import ru.cybercasino.ui.White
import ru.cybercasino.ui.elements.AppTopAppBarUserProfile
import ru.cybercasino.ui.elements.CyberButton
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.ListDevider

@Composable
fun MainProfileScreen(
    onEnterClickListener: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
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
        content = {
            ConstraintLayout(
                constraintSet = ConstraintSet {
                    val refBackgroundImage = createRefFor("backgroundImage")
                    val refProfileTitle = createRefFor("profileTitle")
                    val refCyberButton = createRefFor("cyberButton")
                    val refNewsListDevider = createRefFor("newsListDevider")

                    constrain(refBackgroundImage) {
                        top.linkTo(parent.top, 0.dp)
                        start.linkTo(parent.start, 0.dp)
                        end.linkTo(parent.end, 0.dp)
                        bottom.linkTo(parent.bottom, 0.dp)
                    }

                    constrain(refProfileTitle) {
                        top.linkTo(parent.top, 48.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                    }

                    constrain(refCyberButton) {
                        top.linkTo(refProfileTitle.bottom, 18.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                    }

                    constrain(refNewsListDevider) {
//                        top.linkTo(refCyberButton.bottom, 270.dp)
                        bottom.linkTo(parent.bottom, 70.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                },
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Image(
                    painterResource(id = R.drawable.background_main_play),
                    contentDescription = "",
                    modifier = Modifier
                        .layoutId("backgroundImage")
                        .fillMaxWidth()
                        .fillMaxHeight()
                )

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
                        .layoutId("profileTitle")
                        .padding(start = 24.dp, end = 24.dp),
                    fontSize = 32.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )

                CyberButton(
                    title = stringResource(id = R.string.play_label),
                    titleSize = 16.sp,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .layoutId("cyberButton")
                        .fillMaxWidth()
                        .height(44.dp)
                        .padding(start = 28.dp, end = 28.dp)
                )

                ListDevider(
                    deviderLabel = stringResource(id = R.string.news_label),
                    modifier = Modifier.layoutId("newsListDevider")
                )

            }
        }
    )
}