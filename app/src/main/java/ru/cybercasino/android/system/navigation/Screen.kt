package ru.cybercasino.android.system.navigation

sealed class Screen(val route: String) {
    object Login : Screen("user/login")
}