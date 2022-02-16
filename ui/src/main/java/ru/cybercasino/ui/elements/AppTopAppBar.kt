package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    CyberButton(
                        stringResource(id = buttonLabelTextId),
                        titleSize = 10.sp,
                        onClick = { onButtonClickListener() },
                        Modifier
                            .padding(top = 14.dp)
                            .width(92.dp)
                            .height(21.dp)
                    )
                }
            }
        }
    )
}