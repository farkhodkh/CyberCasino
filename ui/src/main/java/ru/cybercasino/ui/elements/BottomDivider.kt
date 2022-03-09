package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.BottomDividerColor
import ru.cybercasino.ui.R

@Composable
fun BottomDivider() {
    Box(
        modifier = Modifier.padding(top = 28.dp, bottom = 50.dp)
    ) {
        Divider(
            thickness = 3.dp,
            color = BottomDividerColor,
            modifier = Modifier.padding(top = 18.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_red_circle),
            contentDescription = "",
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BottomDividerPreview() {
    BottomDivider()
}