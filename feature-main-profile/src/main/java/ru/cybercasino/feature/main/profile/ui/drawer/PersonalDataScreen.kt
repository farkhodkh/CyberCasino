package ru.cybercasino.feature.main.profile.ui.drawer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppBottomBar
import ru.cybercasino.ui.elements.AppTopBarMainProfile
import ru.cybercasino.ui.elements.SimpleCheckboxComponent

@Composable
fun PersonalDataScreen(
    onBackButtonClick: () -> Unit,
    onEditProfileClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val viewModel = getViewModel<MainProfileViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBarMainProfile(
                menuIcon = R.drawable.ic_arrow_left,
                barLabelTextId = R.string.personal_data,
                isHasNewNotification = state.isHasNewNotification,
                onMenuButtonClickListener = onBackButtonClick,
                onUserProfileButtonClickListener = onEditProfileClick,
            )
        },
        modifier = Modifier
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
            .background(DarkGray)
            .fillMaxSize(),
        bottomBar = {
            AppBottomBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 28.dp, end = 28.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 48.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile_big),
                    modifier = Modifier.align(Alignment.TopCenter),
                    contentDescription = ""
                )
                IconButton(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { onEditProfileClick() }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = ""
                    )
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.loyalty_level),
                color = BlueGrey,
                fontSize = 12.sp
            )

            val progress by remember { mutableStateOf(0.3f) }
            val animatedProgress = animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            ).value

            LinearProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .width(120.dp)
                    .height(16.dp)
                    .padding(top = 6.dp)
                    .align(Alignment.CenterHorizontally),
                color = LightBlue,
                backgroundColor = DarkBlue,
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = BlueGrey, fontSize = 16.sp)) {
                        append(stringResource(id = R.string.experience) + " ")
                    }

                    withStyle(style = SpanStyle(color = White, fontSize = 16.sp)) {
                        append("15 " + stringResource(id = R.string.points))
                    }
                },
                color = BlueGrey,
                fontSize = 12.sp
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.casino_id),
                color = BlueGrey,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
                text = "06606104",
                color = White,
                fontSize = 16.sp
            )

            var nickТameText by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = BlueGrey,
                    focusedIndicatorColor = LightBlue,
                    backgroundColor = DarkBlue
                ),
                value = nickТameText,
                onValueChange = {
                    nickТameText = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.nick_name),
                        fontSize = 14.sp,
                        color = if (nickТameText.text.isEmpty()) DarkGray else White
                    )
                },
            )

            var nameText by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = BlueGrey,
                    focusedIndicatorColor = LightBlue,
                    backgroundColor = DarkBlue
                ),
                value = nameText,
                onValueChange = {
                    nameText = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.name),
                        fontSize = 14.sp,
                        color = if (nameText.text.isEmpty()) DarkGray else White
                    )
                },
            )

            var surNameText by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = BlueGrey,
                    focusedIndicatorColor = LightBlue,
                    backgroundColor = DarkBlue
                ),
                value = surNameText,
                onValueChange = {
                    surNameText = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.sur_name),
                        fontSize = 14.sp,
                        color = if (surNameText.text.isEmpty()) DarkGray else White
                    )
                },
            )

            var middleNameText by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = BlueGrey,
                    focusedIndicatorColor = LightBlue,
                    backgroundColor = DarkBlue
                ),
                value = middleNameText,
                onValueChange = {
                    middleNameText = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.middle_name),
                        fontSize = 14.sp,
                        color = if (middleNameText.text.isEmpty()) DarkGray else White
                    )
                },
            )

            Text(
                text = stringResource(id = R.string.gender),
                modifier = Modifier.padding(top = 24.dp),
                color = DarkGray,
                fontSize = 10.sp
            )

            val maleCheckedState = mutableStateOf(false)

            Row(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                SimpleCheckboxComponent(
                    modifier = Modifier,
                    titleResourceId = R.string.male,
                    checkedStateValue = maleCheckedState,
                    onCheckedChanged = { value -> }
                )

                SimpleCheckboxComponent(
                    modifier = Modifier.padding(start = 14.dp),
                    titleResourceId = R.string. female,
                    checkedStateValue = maleCheckedState,
                    onCheckedChanged = { value -> }
                )
            }

            Text(
                text = stringResource(id = R.string.date_of_birth),
                modifier = Modifier.padding(top = 12.dp),
                color = DarkGray,
                fontSize = 10.sp
            )

            Spacer(modifier = Modifier
                .weight(1f)
                .padding(bottom = 160.dp))
        }
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PersonalDataScreenPreview() {
    PersonalDataScreen({}, {})
}