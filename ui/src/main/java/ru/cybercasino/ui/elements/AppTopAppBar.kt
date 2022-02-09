package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.R

/**
 * Application top bar screen
 * @param buttonLabelTextId - Id for button text
 * @param onButtonClickListener - On button click action
 */
@Composable
fun AppTopAppBar(
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu"
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
                        modifier = Modifier.padding(start = 60.dp)
                    )
                    CyberButton(
                        stringResource(id = buttonLabelTextId),
                        onClick = { onButtonClickListener() },
                        Modifier
                            .padding(top = 14.dp)
                            .width(142.dp)
                            .height(21.dp)
                    )
                }
            }
        }
    )
}