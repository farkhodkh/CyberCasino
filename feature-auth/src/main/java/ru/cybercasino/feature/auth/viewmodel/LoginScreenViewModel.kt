package ru.cybercasino.feature.auth.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.cybercasino.feature.auth.LoginController
import ru.cybercasino.ui.utils.CountryCodeAndFlag
import ru.cybercasino.ui.utils.defaultCountryData

/**
 * Login view model
 */
class LoginScreenViewModel(
    private val loginController: LoginController
) : ViewModel() {

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
    val _state: MutableStateFlow<State> = MutableStateFlow(InitialState)
    val state: StateFlow<State> = _state

    private val _resendTimeout: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * Resend verification code timeout indicator
     */
    val resendTimeout: StateFlow<String> = _resendTimeout

    init {
        val timer = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _resendTimeout.tryEmit("0:${millisUntilFinished/1000}")
            }

            override fun onFinish() {
                _resendTimeout.tryEmit("")
            }
        }
        timer.start()
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
         * User e-mail
         */
        val email: String,

        /**
         * User phone
         */
        val phone: String,

        /**
         * User country
         */
        val selectedCountry: CountryCodeAndFlag,

        /**
         * User country
         */
        val password: String,

        /**
         * User promo code
         */
        val promoCodeText: String,

        /**
         * User privacy policy accepted
         */
        val privacyPolicyCheckedState: Boolean,

        /**
         * Usernews and offers accepted
         */
        val newsAndOffersCheckedState: Boolean,

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

    fun updateViewState(
        email: String? = null,
        phone: String? = null,
        selectedCountry: CountryCodeAndFlag? = null,
        password: String? = null,
        promoCodeText: String? = null,
        privacyPolicyCheckedState: Boolean? = null,
        newsAndOffersCheckedState: Boolean? = null,
    ) {
        val isFieldsCorrect =
            !email.isNullOrEmpty() &&
                    !phone.isNullOrEmpty() &&
                    !password.isNullOrEmpty() &&
                    privacyPolicyCheckedState == true &&
                    newsAndOffersCheckedState == true

        val currentState = _state.value

        val newState = _state.value.copy(
            phone = phone ?: currentState.phone,
            email = email ?: currentState.email,
            selectedCountry = selectedCountry ?: currentState.selectedCountry,
            password = password ?: currentState.password,
            promoCodeText = promoCodeText ?: currentState.promoCodeText,
            privacyPolicyCheckedState = privacyPolicyCheckedState ?: currentState.privacyPolicyCheckedState,
            newsAndOffersCheckedState = privacyPolicyCheckedState ?: currentState.newsAndOffersCheckedState,
            isFieldsCorrect = isFieldsCorrect
        )

        _state.tryEmit(newState)
    }

    fun onPasswordChanged(pass: String) {
        state.value.passwordRequirementsState = (0..3).random()
    }

    fun login() {
        viewModelScope.launch {
            loginController
                .login()
        }
    }

    fun register() {
        viewModelScope.launch {
            loginController
                .registerNewUser()
        }
    }
}

private val InitialState = LoginScreenViewModel.State(
    isLoading = false,
    isAuthorised = false,
    email = "Alabay@gmail.com",
    phone = "9654513156",
    selectedCountry = defaultCountryData,
    password = "",
    promoCodeText = "",
    privacyPolicyCheckedState = false,
    newsAndOffersCheckedState = false,
    isFieldsCorrect = false,
    0,
    PasswordVerificationType.EMailVerification
)

sealed class PasswordVerificationType {
    object EMailVerification: PasswordVerificationType()
    object PhoneVerification: PasswordVerificationType()
}
