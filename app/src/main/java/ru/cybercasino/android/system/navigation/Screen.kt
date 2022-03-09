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
    object Authorization : Screen("auth/login")

    /**
     * The class to navigate to the Registration screen
     */
    object Registration : Screen("auth/registration")

    /**
     * The login verification state screen
     */

    object VerificationScreen: Screen("auth/validate-user")

    /**
     * The user profile state screen
     */
    object MainProfileScreen: Screen("profile/user")

    /**
     * The main profile state screen
     */
    object UserProfileScreen: Screen("profile/main")

    /**
     * The choose language screen
     */
    object ChooseLanguageScreen: Screen("profile/choose_language")
}