package ru.cybercasino.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.CyberButtonColorEnd
import ru.cybercasino.ui.CyberButtonColorStart
import ru.cybercasino.ui.White

@Composable
fun CyberButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradient =
        Brush.horizontalGradient(listOf(CyberButtonColorStart, CyberButtonColorEnd))

    val shape = RoundedCornerShape(12.dp, 4.dp, 12.dp, 4.dp)

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = White
            ),
        ) {
            Text(
                text = title,
                fontSize = 10.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient, shape)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CyberButtonPreview() {
    CyberButton(
        "Регистрация",
        {},
        Modifier
            .width(140.dp)
            .height(41.dp)
    )
}