package ru.cybercasino.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.DarkGray
import ru.cybercasino.ui.White

/**
 * App default text field
 */
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