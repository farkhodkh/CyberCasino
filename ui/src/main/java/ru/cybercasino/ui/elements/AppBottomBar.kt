package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.R

@Composable
fun AppBottomBar() {
    val selectedItem = remember { mutableStateOf("upload") }

    BottomAppBar(
        modifier = Modifier.padding(bottom = 50.dp),
        backgroundColor = DarkBlue
    ) {
        BottomNavigation(
            backgroundColor = DarkBlue,
        ) {
            BottomNavigationItem(
                icon = {
                    Image(painter = painterResource(R.drawable.ic_fb), "")
                },
                label = { Text(text = stringResource(id = R.string.Facebook)) },
                selected = selectedItem.value == "Facebook",
                onClick = {
                    selectedItem.value = "Facebook"
                },
                alwaysShowLabel = false
            )

            BottomNavigationItem(
                icon = {
                    Image(painter = painterResource(R.drawable.ic_google), "")
                },
                label = { Text(text = stringResource(id = R.string.Google)) },
                selected = selectedItem.value == "Google",
                onClick = {
                    selectedItem.value = "Google"
                },
                alwaysShowLabel = false
            )

            BottomNavigationItem(
                icon = {
                    Image(painter = painterResource(R.drawable.ic_tg), "")
                },
                label = { Text(text = stringResource(id = R.string.Telegram)) },
                selected = selectedItem.value == "Telegram",
                onClick = {
                    selectedItem.value = "Telegram"
                },
                alwaysShowLabel = false
            )

            BottomNavigationItem(
                icon = {
                    Image(painter = painterResource(R.drawable.ic_inst), "")
                },
                label = { Text(text = stringResource(id = R.string.Instagram)) },
                selected = selectedItem.value == "Instagram",
                onClick = {
                    selectedItem.value = "Instagram"
                },
                alwaysShowLabel = false
            )
        }
    }
}
