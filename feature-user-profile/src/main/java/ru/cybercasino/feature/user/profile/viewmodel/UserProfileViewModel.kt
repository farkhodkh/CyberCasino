package ru.cybercasino.feature.user.profile.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserProfileViewModel : ViewModel() {
    val _state: MutableStateFlow<State> = MutableStateFlow(InitialState)
    val state: StateFlow<State> = _state

    /**
     * State of the screen.
     */
    data class State(
        /**
         * Demo
         */
        val desc: String,

        /**
         * Is there new notifications for the user
         */
    val isHasNewNotification: Boolean
    )
}

private val InitialState = UserProfileViewModel.State(
    desc = "initial state",
    isHasNewNotification = true
)