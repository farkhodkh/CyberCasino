package ru.cybercasino.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

/**
 * The app default bordered button style
 */
@Composable
fun CyberButtonWithBorder(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradient =
        Brush.horizontalGradient(listOf(CyberButtonColorEnd, CyberButtonColorStart))
    val shape = RoundedCornerShape(16.dp, 5.dp, 16.dp, 5.dp)

    OutlinedButton(
        modifier = modifier,
        onClick = { onClick() },
        border = BorderStroke(1.dp, gradient),
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = White
        )
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CyberButtonWithBorderPreview() {
    CyberButtonWithBorder(
        "Войти",
        {},
        Modifier
            .width(320.dp)
            .height(44.dp)
    )
}