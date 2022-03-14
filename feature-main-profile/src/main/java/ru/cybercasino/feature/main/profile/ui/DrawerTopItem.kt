package ru.cybercasino.feature.main.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.feature.main.profile.domain.NavDrawerItem
import ru.cybercasino.ui.Dark
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.White

@Composable
fun DrawerTopItem(
    item: NavDrawerItem,
    selected: Boolean,
    onItemClick: (NavDrawerItem) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(64.dp)
            .background(DarkBlue)
            .border(1.dp, Dark)
            .padding(start = 10.dp),
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )

        Spacer(modifier = Modifier.width(7.dp))

        Column {
            Text(
                text = stringResource(id = item.titleId),
                fontSize = 10.sp,
                color = White
            )

            Text(
                text = "0.00 USD",
                fontSize = 16.sp,
                color = White
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DrawerItemPreview() {
    DrawerTopItem(
        item = NavDrawerItem.RealAccount,
        selected = false,
        onItemClick = {}
    )
}