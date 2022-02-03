package ru.cybercasino.feature.auth.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Login view model
 */
class LoginScreenViewModel : ViewModel() {

    /**
     * TODO - update after repo added
     * State for bottom navigation cart
     */
//    val state: StateFlow<State> = getCart().filter { it.isSuccess }.map {
//        it.getOrNull()?.let { cart ->
//            State(
//                cart.totalPrice,
//                cart.products.count()
//            )
//        } ?: InitialState
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), InitialState)
    val state: StateFlow<State> = MutableStateFlow(InitialState)


    /**
     * State of the screen.
     */
    data class State(
        /**
         * If the user authorised
         */
        val isAuthorised: Boolean,

        /**
         * state before authorization state checked
         */
        val isLoading: Boolean
    )
}

private val InitialState = LoginScreenViewModel.State(
    isAuthorised = false,
    isLoading = false
)