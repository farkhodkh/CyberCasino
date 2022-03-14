package ru.cybercasino.feature.main.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.BlueGrey
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White

@Composable
fun DrawerHeader(
    onHeaderClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onHeaderClick() }
    ) {
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile button"
            )
        }

        Column {

            Text(
                text = "Alabay@gmail.com",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Новичок 1%",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = BlueGrey,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}