package ru.cybercasino.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.DarkGray
import ru.cybercasino.ui.White

@Composable
fun TextFieldComponent(
    fieldId: String,
    textLabelId: Int,
    onValueChange: (fieldRemember: TextFieldValue) -> Unit
) {
    var fieldRemember by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .layoutId(fieldId),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = DarkBlue
        ),
        value = fieldRemember,
        onValueChange = {
            fieldRemember = it
//            onValueChange(it)
        },
        label = {
            Text(
                text = stringResource(id =textLabelId),
                fontSize = 10.sp,
                color = if (fieldRemember.text.isEmpty()) DarkGray else White
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = textLabelId),
                fontSize = 14.sp,
                color = White
            )
        },
    )
}