package ru.cybercasino.feature.auth.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cybercasino.core.network.common.DefaultHttpErrorSchema
import ru.cybercasino.core.network.common.ResponseSchema
import ru.cybercasino.feature.auth.ClientStatus
import ru.cybercasino.feature.auth.LoginController
import ru.cybercasino.feature.auth.api.AuthenticationStorageRepository
import ru.cybercasino.feature.auth.api.LoginInfo
import ru.cybercasino.feature.auth.api.requests.*
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
        loginController
            .loginState
            .onEach { loginState ->
                loginState.response?.let {
                    catchAuthenticationResponse(it)
                }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            authenticationStorageRepository
                .getLoginInfo()
                .collect { loginInfo ->
                    loginInfo?.let {
                        userLoginInfo = it
                        if (it.status == ClientStatus.LOGGED_IN) {
                            _state.tryEmit(_state.value.copy(isAuthorised = true))
                        }
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

    fun updateLoginViewState(
        email: String? = null,
        phone: String? = null,
        selectedCountry: CountryCodeAndFlag? = null,
        password: String? = null,
        promoCodeText: String? = null,
        verificationCode: String? = null,
        passwordVerificationType: PasswordVerificationType? = null
    ) {
        val currentState = _state.value

        //TODO - Перенести всю эту байде в поля get
        val isFieldsCorrect =
            ((email ?: currentState.email.orEmpty()).isNotEmpty() ||
                    (phone ?: currentState.phone.orEmpty()).isNotEmpty()) &&
                    ((password ?: currentState.password).isNotEmpty())

        val newState = _state.value.copy(
            phone = phone ?: currentState.phone,
            email = email ?: currentState.email,
            selectedCountry = selectedCountry ?: currentState.selectedCountry,
            password = password ?: currentState.password,
            promoCodeText = promoCodeText ?: currentState.promoCodeText,
            isFieldsCorrect = isFieldsCorrect,
            verificationCode = verificationCode ?: "",
            passwordVerificationType = passwordVerificationType
                ?: currentState.passwordVerificationType
        )

        _state.tryEmit(newState)
    }

    fun onPasswordChanged(pass: String) {
        state.value.passwordRequirementsState = (0..3).random()
    }

    fun login() {
        viewModelScope.launch {
            val currentState = _state.value

            authenticationStorageRepository.setLoginEmail(currentState.email)
            authenticationStorageRepository.setLoginPhone(currentState.phone)
            authenticationStorageRepository.setPass(currentState.password)

            viewModelScope.launch {

                val request = when (currentState.passwordVerificationType) {
                    PasswordVerificationType.EMailVerification -> {
                        LoginRequestSchema(
                            email = currentState.email ?: "",
                            password = currentState.password
                        )
                    }
                    PasswordVerificationType.PhoneVerification -> {
                        LoginRequestSchema(
                            phone = currentState.phone ?: "",
                            password = currentState.password
                        )
                    }
                }

                loginController
                    .login(request)
            }
        }
    }

    fun validateUser() {
        viewModelScope.launch {
            val currentState = _state.value

            authenticationStorageRepository.setLoginEmail(currentState.email)
            authenticationStorageRepository.setLoginPhone(currentState.phone)
            authenticationStorageRepository.setPass(currentState.password)

            val request = when (currentState.passwordVerificationType) {
                PasswordVerificationType.EMailVerification -> {
                    UserValidationRequestSchema(
                        email = currentState.email,
                        password = currentState.password,
                        reset = false,
                    )
                }
                PasswordVerificationType.PhoneVerification -> {
                    UserValidationRequestSchema(
                        phone = currentState.phone,
                        password = currentState.password,
                        reset = false,
                    )
                }
            }

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

    private fun catchAuthenticationResponse(response: ResponseSchema) {
        when (response) {
            is UserValidationResponseSchema -> {
                _state.tryEmit(
                    _state.value.copy(
                        emailErrors = response.email ?: emptyList(),
                        passwordErrors = response.password ?: emptyList(),
                        phoneErrors = response.phone ?: emptyList()
                    )
                )
                when (_state.value.passwordVerificationType) {
                    PasswordVerificationType.EMailVerification -> {
                        if (response.password.isNullOrEmpty() && response.email.isNullOrEmpty()) {
                            _state.tryEmit(_state.value.copy(verificationCodeRequest = true))
                            sendCode()
                        } else {
                            setLoginDefaultState()
                        }
                    }
                    PasswordVerificationType.PhoneVerification -> {
                        if (response.password.isNullOrEmpty() && response.phone.isNullOrEmpty()) {
                            _state.tryEmit(_state.value.copy(verificationCodeRequest = true))
                            sendCode()
                        } else {
                            setLoginDefaultState()
                        }
                    }
                }
            }
            is CheckCodeResponseSchema -> {
                _state.tryEmit(
                    _state.value.copy(
                        emailErrors = response.email ?: emptyList(),
                        verificationCodeError = response.code ?: "",
                        phoneErrors = response.phone ?: emptyList()
                    )
                )

                when (_state.value.passwordVerificationType) {
                    PasswordVerificationType.EMailVerification -> {
                        if (response.code.isNullOrEmpty() && response.email.isNullOrEmpty()) {
                            viewModelScope.launch {
                                register()
                            }
                        }
                    }
                    PasswordVerificationType.PhoneVerification -> {
                        if (response.code.isNullOrEmpty() && response.phone.isNullOrEmpty()) {
                            viewModelScope.launch {
                                register()
                            }
                        }
                    }
                }
            }
            is RegistrationResponseSchema -> {
                viewModelScope.launch {
                    //TODO - go to login view
                    //authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
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
            is LoginResponseSchema -> {
                viewModelScope.launch {
                    authenticationStorageRepository.setLoginEmail(response.email)
                    authenticationStorageRepository.setToken(response.token)
                    authenticationStorageRepository.setUser(response.user)
                    authenticationStorageRepository.setPass(response.password)
                }

                when (_state.value.passwordVerificationType) {
                    PasswordVerificationType.EMailVerification -> {
                        if (response.password.isNullOrEmpty() && response.email.isNullOrEmpty()) {
                            viewModelScope.launch {
                                authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                            }
                        }
                    }
                    PasswordVerificationType.PhoneVerification -> {
                        if (response.password.isNullOrEmpty() && response.phone.isNullOrEmpty()) {
                            viewModelScope.launch {
                                authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                            }
                        }
                    }
                }
            }
            is DefaultHttpErrorSchema -> {
                _state.tryEmit(
                    _state.value.copy(
                        emailErrors = response.email ?: emptyList(),
                        passwordErrors = response.password ?: emptyList(),
                        phoneErrors = response.phone ?: emptyList()
                    )
                )
                when (_state.value.passwordVerificationType) {
                    PasswordVerificationType.EMailVerification -> {
                        if (response.email.isNullOrEmpty() && response.password.isNullOrEmpty()) {
                            viewModelScope.launch {
                                login()
                            }
                        }
                    }
                    PasswordVerificationType.PhoneVerification -> {
                        if (response.phone.isNullOrEmpty() && response.password.isNullOrEmpty()) {
                            viewModelScope.launch {
                                login()
                            }
                        }
                    }
                }
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

    private fun setLoginDefaultState() {
        viewModelScope.launch {
            authenticationStorageRepository.setLoginEmail("")
            authenticationStorageRepository.setLoginPhone("")
            authenticationStorageRepository.setPass("")
            authenticationStorageRepository.setStatus(ClientStatus.NOT_LOGGED_IN)
        }
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

