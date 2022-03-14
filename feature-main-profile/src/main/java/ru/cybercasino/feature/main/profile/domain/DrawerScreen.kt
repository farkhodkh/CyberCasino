package ru.cybercasino.feature.main.profile.domain

/**
 * Application main drawer screen description class
 */
sealed class DrawerScreen(
    /**
     * Screen name description
     */
    val route: String
) {
    /**
     * The class to navigate to the Authorization screen
     */
    object Profile : DrawerScreen("auth/login")
}