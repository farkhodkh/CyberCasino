package ru.cybercasino.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cybercasino.ui.DarkBlue
import ru.cybercasino.ui.DarkGray

/**
 * Verification code enter screen
 */
@Composable
fun RegistrationCodeInputScreen(
    onCodeEnter: (String) -> Unit,
    modifier: Modifier
) {
    RegistrationCodeInput(
        onCodeEnter = onCodeEnter,
        modifier = Modifier.then(modifier),
        codeLength = 4
    )
}

@Composable
private fun RegistrationCodeInput(
    onCodeEnter: (String) -> Unit,
    modifier: Modifier,
    codeLength: Int
) {
    val code = remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier), contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = code.value,
            onValueChange = {

                if (it.length <= codeLength) {
                    code.value = it
                    if (it.length == codeLength) {
                        onCodeEnter(it)
                    } else {
                        onCodeEnter("")
                    }
                }
            },
            Modifier.focusRequester(focusRequester = focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                CodeInputDecoration(code.value, codeLength)
            }
        )
    }
}

@Composable
private fun CodeInputDecoration(code: String, length: Int) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(
                border = BorderStroke(2.dp, color = DarkBlue)
            )
    ) {
        Row {
            for (i in 0 until length) {
                val text = if (i < code.length) code[i].toString() else "X"
                CodeEntry(text)
            }
        }
    }
}

@Composable
private fun CodeEntry(text: String) {
    Box(
        modifier = Modifier
            .width(42.dp)
            .height(42.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 28.sp,
            color = DarkGray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordVerificationScreenPreview() {
    RegistrationCodeInputScreen(onCodeEnter = {}, modifier = Modifier)
}