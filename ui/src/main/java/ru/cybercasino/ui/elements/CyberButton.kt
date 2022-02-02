package ru.cybercasino.ui.elements

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.CyberButtonColorEnd
import ru.cybercasino.ui.CyberButtonColorStart
import ru.cybercasino.ui.White

@Composable
fun CyberButton(title: String, onClick: () -> Unit) {
    val gradient =
        Brush.horizontalGradient(listOf(CyberButtonColorStart, CyberButtonColorEnd))

    Button(
        modifier = Modifier
            .width(92.dp)
            .height(20.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
        ) {
            Text(
                text = title,
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CyberButtonPreview() {
    CyberButton("Регистрация", {})
}