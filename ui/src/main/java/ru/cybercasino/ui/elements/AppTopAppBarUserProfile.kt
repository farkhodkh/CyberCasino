package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.R
import ru.cybercasino.ui.utils.defaultCountryData

@Composable
fun AppTopAppBarUserProfile(
    barLabelTextId: Int,
    isHasNewNotification: Boolean,
    onNotificationButtonClickListener: () -> Unit,
    onUserProfileButtonClickListener: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.padding(top = 24.dp),
        navigationIcon = {
            IconButton(
                onClick = {
                    onNotificationButtonClickListener()
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
                        modifier = Modifier.padding(start = 60.dp, end = 40.dp)
                    )

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
                            painter = painterResource(id = R.drawable.ic_profile_button),
                            contentDescription = "Profile button"
                        )
                    }
                }
            }
        }
    )
}