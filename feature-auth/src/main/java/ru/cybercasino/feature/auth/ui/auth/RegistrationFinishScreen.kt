@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalPagerApi::class
)

package ru.cybercasino.feature.auth.ui.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.ui.R
import ru.cybercasino.ui.elements.AppTopAppBar
import ru.cybercasino.ui.elements.CyberButton

@Composable
fun RegistrationFinishScreen(
    onEnterClickListener: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    var selectedTabIndex by remember { mutableStateOf(0) }

    val viewModel = getViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopAppBar(
                buttonLabelTextId = R.string.enter_text,
                onButtonClickListener = onEnterClickListener
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.registration_finish),
                        style = TextStyle(
                            fontSize = 24.sp
                        ),
                        modifier = Modifier
                            .padding(top = 50.dp, start = 16.dp, end = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.image_configurations),
                        contentDescription = "",
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 35.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.congratulations_registration_finish),
                        style = TextStyle(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 50.dp)
                    )

                    CyberButton(
                        title = stringResource(id = R.string.enter_text_2),
                        16.sp,
                        onClick = { onEnterClickListener() },
                        modifier = Modifier
                            .padding(top = 28.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(44.dp)
                    )
                }
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegistrationFinishScreenPreview() {
    RegistrationFinishScreen({})
}