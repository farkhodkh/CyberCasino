package ru.cybercasino.feature.auth.viewmodel

import android.os.CountDownTimer
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

    private val _resendTimeout: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * Resend verification code timeout indicator
     */
    val resendTimeout: StateFlow<String> = _resendTimeout


    private val resendTimeoutMills = 60000L
    private val countDownInterval = 1000L

    init {
        startVerificationCodeRequireCountdown()
    }

    /**
     * State of the screen.
     */
    data class State(

        /**
         * state before authorization state checked
         */
        val isLoading: Boolean,

        /**
         * If the user authorised
         */
        val isAuthorised: Boolean,

        /**
         * User login
         */
        val userLogin: String,

        /**
         * If the all required fields correctly filled
         */
        val isFieldsCorrect: Boolean,

        /**
         * Password requirements label text
         */
        var passwordRequirementsState: Int,

        /**
         * The type of the user password verification type
         */
        var passwordVerificationType: PasswordVerificationType,
    )

    fun onPasswordChanged(pass: String) {
        state.value.passwordRequirementsState = (0..3).random()
    }

    fun requireVerificationCode() {
        startVerificationCodeRequireCountdown()
    }

    private fun startVerificationCodeRequireCountdown() {
        val timer = object : CountDownTimer(resendTimeoutMills, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val lastTime = String.format("0:%02d", millisUntilFinished / 1000)
                _resendTimeout.tryEmit(lastTime)
            }

            override fun onFinish() {
                _resendTimeout.tryEmit("")
            }
        }
        timer.start()
    }
}

private val InitialState = LoginScreenViewModel.State(
    isLoading = false,
    isAuthorised = false,
    userLogin = "Alabay@gmail.com",
    isFieldsCorrect = true,
    0,
    PasswordVerificationType.EMailVerification
)

sealed class PasswordVerificationType {
    object EMailVerification : PasswordVerificationType()
    object PhoneVerification : PasswordVerificationType()
}
