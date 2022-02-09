package ru.cybercasino.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.DarkGray
import ru.cybercasino.ui.LightBlue

@Composable
fun PasswordVerificationElementScreen(
    fieldId: String
) {
    var verificationCodeItem by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        textStyle = TextStyle(
            color = DarkGray,
            fontSize = 28.sp,
        ),
        modifier = Modifier
            .width(36.dp)
//            .height(22.dp)
            .layoutId(fieldId),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = DarkBlue,
            focusedIndicatorColor = LightBlue,
//            unfocusedIndicatorColor = LightBlue,
        ),
        value = verificationCodeItem,
        onValueChange = {
            verificationCodeItem = TextFieldValue(it.text.last().toString())
        },
        placeholder = {
            Text(
                text = "X",
                fontSize = 28.sp,
                color = DarkGray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
    )
}

@Composable
fun PasswordVerificationScreen(
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .then(modifier),
    ) {
        (1..5).forEach { id ->
            PasswordVerificationElementScreen(fieldId = "fieldId_$id")
            Column(
                modifier = Modifier
                    .width(6.dp)
            ) {}
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordVerificationScreenPreview() {
    PasswordVerificationScreen(modifier = Modifier)
}