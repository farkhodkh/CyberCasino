@file:OptIn(ExperimentalMaterialApi::class)

package ru.cybercasino.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val ColorPalette = lightColors(
    primary = Dark,
    primaryVariant = BlueGrey,
    onPrimary = White,

    secondary = СornflowerBlue,
    secondaryVariant = Yelow,
    onSecondary = White,

    surface = DarkGray,
    onSurface = White,

    background = DarkBlue,
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
