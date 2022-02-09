package ru.cybercasino.android.system.navigation

/**
 * Application main fragments description class
 */
sealed class Screen(
    /**
     * Screen name description
     */
    val route: String
) {
    /**
     * The class to navigate to the Authorization screen
     */
    object Login : Screen("user/login")

    /**
     * The class to navigate to the Registration screen
     */
    object Registration : Screen("user/registration")

    /**
     * The login verification state screen
     */

    object VerificationScreen: Screen("user/verification")
}