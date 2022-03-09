@file:OptIn(ExperimentalMaterialApi::class)

package ru.cybercasino.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val ColorPalette = lightColors(
    primary = Dark,
    primaryVariant = BlueGrey,
    onPrimary = White,

    secondary = Blue1,
    secondaryVariant = Yellow,
    onSecondary = White,

    surface = DarkGray,
    onSurface = White,

    background = Dark,
    onBackground = White,
)

/**
 * Main application theme.
 *
 * @param content The content to apply theme to.
 */
@Composable
fun CyberCasinoTheme(content: @Composable () -> Unit) {
    val sysUiController = rememberSystemUiController()

    SideEffect {
        sysUiController.setSystemBarsColor(
            color = Dark,
            darkIcons = true,
            isNavigationBarContrastEnforced = false
        )
    }

    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
