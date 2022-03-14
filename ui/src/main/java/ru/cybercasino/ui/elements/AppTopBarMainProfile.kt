package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White

@Composable
fun AppTopBarMainProfile(
    menuIcon: Int = R.drawable.ic_menu,
    barLabelTextId: Int = 0,
    isHasNewNotification: Boolean,
    onMenuButtonClickListener: () -> Unit,
    onUserProfileButtonClickListener: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.padding(top = 24.dp),
        navigationIcon = {
            IconButton(
                onClick = {
                    onMenuButtonClickListener()
                }
            ) {
                Image(
                    painter = painterResource(id = menuIcon),
                    contentDescription = ""
                )
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Row {
                    if (barLabelTextId != 0) {
                        Text(
                            modifier = Modifier.padding(top = 10.dp, start = 30.dp, end = 20.dp),
                            text = stringResource(id = barLabelTextId),
                            style = TextStyle(
                                color = White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.ic_logo),
                            contentDescription = "",
                            modifier = Modifier.padding(start = 60.dp, end = 40.dp)
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Box {
                            if (isHasNewNotification) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_new_notifications),
                                    contentDescription = "Profile button",
                                    modifier = Modifier.align(Alignment.TopEnd)
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notification),
                                contentDescription = "Notification button"
                            )
                        }
                    }

                    IconButton(
                        onClick = {}
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = "Profile button"
                        )
                    }
                }
            }
        }
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AppTopBarMainProfilePreview() {
    AppTopBarMainProfile(
        menuIcon = R.drawable.ic_menu,
        barLabelTextId = R.string.enter_text,
        isHasNewNotification = true,
        onMenuButtonClickListener = {},
        onUserProfileButtonClickListener = { },
    )
}