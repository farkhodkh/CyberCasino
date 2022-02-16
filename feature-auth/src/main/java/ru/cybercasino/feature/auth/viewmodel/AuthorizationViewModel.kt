package ru.cybercasino.feature.auth.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cybercasino.feature.auth.ClientStatus
import ru.cybercasino.feature.auth.LoginController
import ru.cybercasino.feature.auth.api.AuthenticationStorageRepository
import ru.cybercasino.feature.auth.api.LoginInfo
import ru.cybercasino.feature.auth.api.requests.CheckCodeRequestSchema
import ru.cybercasino.feature.auth.api.requests.RegistrationRequestSchema
import ru.cybercasino.feature.auth.api.requests.SendCodeRequestSchema
import ru.cybercasino.feature.auth.api.requests.UserValidationRequestSchema
import ru.cybercasino.feature.auth.api.responses.*
import ru.cybercasino.ui.utils.CountryCodeAndFlag
import ru.cybercasino.ui.utils.defaultCountryData

/**
 * Login view model
 */
class LoginScreenViewModel(
    private val loginController: LoginController,
    private val authenticationStorageRepository: AuthenticationStorageRepository
) : ViewModel() {

    val _state: MutableStateFlow<State> = MutableStateFlow(InitialState)
    val state: StateFlow<State> = _state

    private val _resendTimeout: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * Resend verification code timeout indicator
     */
    val resendTimeout: StateFlow<String> = _resendTimeout
    private var userLoginInfo: LoginInfo? = null

    init {

        startVerificationCodeRequestTimer()

        loginController
            .loginState
            .onEach { loginState ->
                loginState.response?.let {
                    catchLoginResponse(it)
                }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            authenticationStorageRepository
                .getLoginInfo()
                .collect { loginInfo ->
                    loginInfo?.let {
                        userLoginInfo = it
                    }
                }
        }
    }

    fun updateViewState(
        email: String? = null,
        phone: String? = null,
        selectedCountry: CountryCodeAndFlag? = null,
        password: String? = null,
        promoCodeText: String? = null,
        verificationCode: String? = null,
        privacyPolicyCheckedState: Boolean? = null,
        newsAndOffersCheckedState: Boolean? = null,
    ) {
        val currentState = _state.value

        //TODO - Перенести всю эту байде в поля get
        val isFieldsCorrect =
            ((email ?: currentState.email.orEmpty()).isNotEmpty() ||
                    (phone ?: currentState.phone.orEmpty()).isNotEmpty()) &&
                    ((password ?: currentState.password).isNotEmpty()) &&
                    (privacyPolicyCheckedState ?: currentState.privacyPolicyCheckedState) &&
                    (newsAndOffersCheckedState ?: currentState.newsAndOffersCheckedState)

        val newState = _state.value.copy(
            phone = phone ?: currentState.phone,
            email = email ?: currentState.email,
            selectedCountry = selectedCountry ?: currentState.selectedCountry,
            password = password ?: currentState.password,
            promoCodeText = promoCodeText ?: currentState.promoCodeText,
            privacyPolicyCheckedState = privacyPolicyCheckedState
                ?: currentState.privacyPolicyCheckedState,
            newsAndOffersCheckedState = newsAndOffersCheckedState
                ?: currentState.newsAndOffersCheckedState,
            isFieldsCorrect = isFieldsCorrect,
            verificationCode = verificationCode ?: "",
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

    fun validateNewUser() {
        viewModelScope.launch {
            val currentState = _state.value

            authenticationStorageRepository.setLoginEmail(currentState.email)
            authenticationStorageRepository.setLoginPhone(currentState.phone)
            authenticationStorageRepository.setPass(currentState.password)

            val request = UserValidationRequestSchema(
                email = currentState.email,
                phone = currentState.phone,
                password = currentState.password,
                reset = false,
            )

            loginController.validateNewUser(request)
        }
    }

    fun sendCode() {
        viewModelScope.launch {

            startVerificationCodeRequestTimer()

            userLoginInfo?.let { loginInfo ->
                val request = SendCodeRequestSchema(
                    email = loginInfo.email,
                    phone = loginInfo.phone,
                    reset = false
                )
                loginController.sendCode(request)
            }
        }
    }

    fun checkCode() {
        viewModelScope.launch {
            val currentState = _state.value
            authenticationStorageRepository.setVerificationCode(currentState.verificationCode)

            val request = CheckCodeRequestSchema(
                code = currentState.verificationCode,
                phone = userLoginInfo?.phone,
                email = userLoginInfo?.email,
                reset = false
            )

            loginController.checkCode(request)
        }
    }

    private fun catchLoginResponse(response: ResponseSchema) {
        when (response) {
            is UserValidationResponseSchema -> {
                response.email?.let {
                    _state.tryEmit(_state.value.copy(emailErrors = it))
                }
                response.password?.let {
                    _state.tryEmit(_state.value.copy(passwordErrors = it))
                }
                response.phone?.let {
                    _state.tryEmit(_state.value.copy(phoneErrors = it))
                }
                if (
                    response.password.isNullOrEmpty()
                    && (response.email.isNullOrEmpty() || response.phone.isNullOrEmpty())
                ) {
                    viewModelScope.launch {
                        _state.tryEmit(_state.value.copy(verificationCodeRequest = true))
                        sendCode()
                    }
                } else {
                    viewModelScope.launch {
                        authenticationStorageRepository.setLoginEmail("")
                        authenticationStorageRepository.setLoginPhone("")
                        authenticationStorageRepository.setPass("")
                        authenticationStorageRepository.setStatus(ClientStatus.NOT_LOGGED_IN)
                    }
                }
            }
            is CheckCodeResponseSchema -> {
                response.code?.let { error ->
                    _state.tryEmit(_state.value.copy(verificationCodeError = error))
                }
                response.email?.let {
                    _state.tryEmit(_state.value.copy(emailErrors = it))
                }
                response.phone?.let {
                    _state.tryEmit(_state.value.copy(phoneErrors = it))
                }
                if (response.code.isNullOrEmpty()
                    && response.email.isNullOrEmpty()
                    && response.phone.isNullOrEmpty()
                ) {
                    viewModelScope.launch {
                        register()
                    }
                }
            }
            is RegistrationResponseSchema -> {
                viewModelScope.launch {
                    authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                }
            }
            is UserResponseSchema -> {
                val b = 0
            }
            is SendCodeResponseSchema -> {
                if (response.email.isNullOrEmpty()
                    || response.phone.isNullOrEmpty()
                ) {
                    viewModelScope.launch {
                        authenticationStorageRepository.setStatus(ClientStatus.VERIFICATION)
                    }
                }
            }
            is AuthenticationResponseSchema -> {
                val b = 0
            }
            else -> {
                val b = 0
            }
        }
    }

    private suspend fun register() {
        viewModelScope.launch {
            //TODO - тут нужна прослойка маппинга
            userLoginInfo?.let { logInfo ->
                val request = RegistrationRequestSchema(
                    email = logInfo.email,
                    phone = logInfo.phone,
                    password = logInfo.password,
                    code = logInfo.verificationCode,
                    currency = "EUR"
                )
                //TODO - END

                //loginController.registerNewUser(request)
            }
        }
    }

    private fun startVerificationCodeRequestTimer() {
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _resendTimeout.tryEmit("0:${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                _resendTimeout.tryEmit("")
            }
        }
        timer.start()
    }

    override fun onCleared() {
        viewModelScope.launch {
            authenticationStorageRepository.setStatus(ClientStatus.NOT_LOGGED_IN)
        }
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
        val email: String?,

        /**
         * E-mail error descriptions list
         */
        val emailErrors: List<String>,

        /**
         * User phone
         */
        val phone: String?,

        /**
         * Phone error descriptions list
         */
        val phoneErrors: List<String>,

        /**
         * User country
         */
        val selectedCountry: CountryCodeAndFlag,

        /**
         * User country
         */
        val password: String,

        /**
         * Password error descriptions list
         */
        val passwordErrors: List<String>,

        /**
         * User promo code
         */
        val promoCodeText: String,

        /**
         * Verification code
         */
        val verificationCode: String,

        /**
         * Verification code check errors
         */
        val verificationCodeError: String,

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
         * Login fields verification state
         */
        val verificationCodeRequest: Boolean,

        /**
         * Password requirements label text
         */
        var passwordRequirementsState: Int,

        /**
         * The type of the user password verification type
         */
        var passwordVerificationType: PasswordVerificationType,
    )
}

private val InitialState = LoginScreenViewModel.State(
    isLoading = false,
    isAuthorised = false,
    email = null,
    emptyList(),
    phone = null,
    emptyList(),
    selectedCountry = defaultCountryData,
    password = "",
    emptyList(),
    promoCodeText = "",
    verificationCode = "",
    "",
    privacyPolicyCheckedState = false,
    newsAndOffersCheckedState = false,
    isFieldsCorrect = false,
    verificationCodeRequest = false,
    0,
    PasswordVerificationType.EMailVerification
)

sealed class PasswordVerificationType {
    object EMailVerification : PasswordVerificationType()
    object PhoneVerification : PasswordVerificationType()
}

