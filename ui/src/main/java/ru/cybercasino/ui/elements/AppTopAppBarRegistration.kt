package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.R
import ru.cybercasino.ui.utils.defaultCountryData

/**
 * Application top bar screen
 * @param buttonLabelTextId - Id for button text
 * @param onButtonClickListener - On button click action
 */
@Composable
fun AppTopAppBarRegistration(
    buttonLabelTextId: Int,
    onButtonClickListener: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.padding(top = 24.dp),
        navigationIcon = {
            IconButton(
                onClick = {
                    //scope.launch { scaffoldState.drawerState.open() }
                }
            ) {
                Text(
                    text = defaultCountryData.flag,
                    fontSize = 16.sp
                )
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 60.dp, end = 40.dp)
                            .weight(0.7f)
                    )
                    CyberButton(
                        stringResource(id = buttonLabelTextId),
                        titleSize = 10.sp,
                        onClick = { onButtonClickListener() },
                        modifier = Modifier
                            .weight(0.3f)
                            .height(28.dp)
                    )
                }
            }
        }
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AppTopAppBarRegistrationPreview() {
    AppTopAppBarRegistration(
        buttonLabelTextId = R.string.registration_text,
        onButtonClickListener = { }
    )
}