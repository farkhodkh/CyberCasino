package ru.cybercasino.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen1() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "KFjhkshgk dglkj ",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun LoginScreen1Preview() {
    LoginScreen1()
}