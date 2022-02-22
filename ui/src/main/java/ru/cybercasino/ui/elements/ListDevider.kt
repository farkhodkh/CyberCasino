package ru.cybercasino.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.R
import ru.cybercasino.ui.White
import androidx.compose.ui.graphics.Color

@Composable
fun ListDevider(
    deviderLabel: String,
    labelColor: Color = White,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_devider_start),
            contentDescription = "",
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = deviderLabel,
            style = TextStyle(
                color = labelColor,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_devider_end),
            contentDescription = "",
            modifier = Modifier.padding(top = 12.dp)
        )
    }


}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CyberButtonPreview() {
    ListDevider("Новости")
}