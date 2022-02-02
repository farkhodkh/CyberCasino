package ru.cybercasino.feature.auth.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.cybercasino.feature.auth.LoginScreen

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

        val isLoading: Boolean
    )
}

private val InitialState = LoginScreenViewModel.State(
    isAuthorised = false,
    isLoading = false
)