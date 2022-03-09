package ru.cybercasino.feature.main.profile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppBottomBar
import ru.cybercasino.ui.elements.AppTopAppBarUserProfile
import ru.cybercasino.ui.utils.getLanguagesList

@Composable
fun ChooseLanguageScreen(
    onChooseLanguage: () -> Unit
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
                onNotificationButtonClickListener = onChooseLanguage,
                onUserProfileButtonClickListener = onChooseLanguage,
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
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            getLanguagesList().forEach { language ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onChooseLanguage() }
                ) {

                    Text(
                        text = language.flag,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 14.dp, start = 28.dp)
                    )

                    Text(
                        text = language.language,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 18.dp, start = 16.dp)
                    )
                }
            }
        }
    }
}