package ru.cybercasino.feature.user.profile.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.R

@Composable
fun MainProfile() {
    Text(
        modifier = Modifier
            .padding(top = 50.dp, start = 16.dp)
            .layoutId("enterTitle"),
        text = "Основной профиль приложения",
        fontSize = 32.sp,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    )
}