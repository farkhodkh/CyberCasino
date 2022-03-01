package ru.cybercasino.feature.auth.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import java.util.*
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

    @Suppress("MagicNumber")
    private val resendTimeoutMills = 60000L
    @Suppress("MagicNumber")
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

    /**
     * Method for password update
     */
    @Suppress("UnusedPrivateMember")
    fun onPasswordChanged(pass: String) {
        @Suppress("MagicNumber")
        state.value.passwordRequirementsState = (0..3).random()
    }

    /**
     * Method to require Verification Code
     */
    fun requireVerificationCode() {
        startVerificationCodeRequireCountdown()
    }

    @Suppress("MagicNumber")
    private fun startVerificationCodeRequireCountdown() {
        val timer = object : CountDownTimer(resendTimeoutMills, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val lastTime = String.format(Locale.getDefault(), "0:%02d", millisUntilFinished / 1000)
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

/**
 * Password verification type class
 */
sealed class PasswordVerificationType {
    /**
     * E-mail based verification
     */
    object EMailVerification : PasswordVerificationType()

    /**
     * Phone based verification
     */
    object PhoneVerification : PasswordVerificationType()
}
