package ru.cybercasino.feature.auth.viewmodel

import android.os.CountDownTimer
import androidx.core.text.isDigitsOnly
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
class AuthorizationViewModel(
    private val loginController: LoginController,
    private val authenticationStorageRepository: AuthenticationStorageRepository
) : ViewModel() {

    val _state: MutableStateFlow<State> = MutableStateFlow(InitialState)
    val state: StateFlow<State> = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), InitialState)

    private val _resendTimeout: MutableStateFlow<String> = MutableStateFlow("0:60")

    /**
     * Resend verification code timeout indicator
     */
    val resendTimeout: StateFlow<String> = _resendTimeout
    private var userLoginInfo: LoginInfo? = null
    private var timer: CountDownTimer? = null

    companion object {
        private var instance: AuthorizationViewModel? = null

        fun getInstance(
            loginController: LoginController,
            authenticationStorageRepository: AuthenticationStorageRepository
        ): AuthorizationViewModel {
            if (instance == null) {
                instance = AuthorizationViewModel(loginController, authenticationStorageRepository)
            }
            return instance!!
        }
    }

    init {
        loginController
            .loginState
            .onEach { loginState ->
                catchAuthenticationResponse(loginState.response)
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
        authentificationType: AuthentificationType? = null
    ) {
        val currentState = _state.value

        val isFieldsCorrect = when (authentificationType ?: currentState.authentificationType) {
            AuthentificationType.EMail -> {
                (email ?: currentState.email.orEmpty()).isNotEmpty()
                        && (password ?: currentState.password).isNotEmpty()
                        && (privacyPolicyCheckedState ?: currentState.privacyPolicyCheckedState)
            }
            AuthentificationType.Phone -> {
                (phone ?: currentState.phone.orEmpty()).isNotEmpty()
                        && (password ?: currentState.password).isNotEmpty()
                        && (privacyPolicyCheckedState ?: currentState.privacyPolicyCheckedState)
            }
        }

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
            authentificationType = authentificationType
                ?: currentState.authentificationType,
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
        authentificationType: AuthentificationType? = null
    ) {
        val currentState = _state.value

        val isFieldsCorrect = when (authentificationType ?: currentState.authentificationType) {
            AuthentificationType.EMail -> {
                (email ?: currentState.email.orEmpty()).isNotEmpty()
                        && (password ?: currentState.password).isNotEmpty()
            }
            AuthentificationType.Phone -> {
                    (phone ?: currentState.phone.orEmpty()).isNotEmpty()
                        && (password ?: currentState.password).isNotEmpty()
            }
        }

        val newState = _state.value.copy(
            phone = phone ?: currentState.phone,
            email = email ?: currentState.email,
            selectedCountry = selectedCountry ?: currentState.selectedCountry,
            password = password ?: currentState.password,
            promoCodeText = promoCodeText ?: currentState.promoCodeText,
            isFieldsCorrect = isFieldsCorrect,
            verificationCode = verificationCode ?: "",
            authentificationType = authentificationType ?: currentState.authentificationType
        )

        _state.tryEmit(newState)
    }

    fun onPasswordChanged(pass: String) {

        var passwordRequirementsState = 0

        if (pass.isNotEmpty()) {
            if (pass.isDigitsOnly())
                passwordRequirementsState += 1

            if (pass.onlyLetters())
                passwordRequirementsState += 2

            if (pass.length < 6)
                passwordRequirementsState += 4

            if (!pass.isDigitsOnly() && !pass.onlyLetters() && pass.length >= 6)
                passwordRequirementsState = 7
        }

        state.value.passwordRequirementsState = passwordRequirementsState
    }

    fun login() {
        viewModelScope.launch {
            val currentState = _state.value

            authenticationStorageRepository.setLoginEmail(currentState.email)
            authenticationStorageRepository.setLoginPhone(currentState.phone)
            authenticationStorageRepository.setPass(currentState.password)

            viewModelScope.launch {

                val request = when (currentState.authentificationType) {
                    AuthentificationType.EMail -> {
                        LoginRequestSchema(
                            email = currentState.email ?: "",
                            password = currentState.password
                        )
                    }
                    AuthentificationType.Phone -> {
                        LoginRequestSchema(
                            phone = "${currentState.selectedCountry.code}${currentState.phone}",
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
            val request = when (currentState.authentificationType) {
                AuthentificationType.EMail -> {
                    authenticationStorageRepository.setLoginEmail(currentState.email)
                    authenticationStorageRepository.setPass(currentState.password)

                    UserValidationRequestSchema(
                        email = currentState.email,
                        password = currentState.password,
                        reset = false,
                    )
                }
                AuthentificationType.Phone -> {
                    authenticationStorageRepository.setLoginPhone("${currentState.selectedCountry.code}${currentState.phone}")
                    authenticationStorageRepository.setPass(currentState.password)

                    UserValidationRequestSchema(
                        phone = "${currentState.selectedCountry.code}${currentState.phone}",
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
            userLoginInfo?.let { loginInfo ->
                val request = SendCodeRequestSchema(
                    email = loginInfo.email,
                    phone = loginInfo.phone,
                    reset = false
                )
                loginController.sendCode(request)
            }
            _state.tryEmit(_state.value.copy(verificationCodeRequested = true))
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

    private fun catchAuthenticationResponse(response: ResponseSchema?) {
        when (response) {
            is UserValidationResponseSchema -> {
                if (response.isSuccessful) {
                    viewModelScope.launch {
                        setEmptyErrorState()
                        authenticationStorageRepository.setStatus(ClientStatus.VERIFICATION)
                        _state.tryEmit(
                            _state.value.copy(
                                email = userLoginInfo?.email,
                                phone = userLoginInfo?.phone,
                                goToVerificationScreen = true
                            )
                        )
                    }
                }
            }
            is CheckCodeResponseSchema -> {
                setEmptyErrorState()
                if (response.isSuccessful) {
                    register()
                } else {
                    when (_state.value.authentificationType) {
                        AuthentificationType.EMail -> {
                            if (response.code.isNullOrEmpty() && response.email.isNullOrEmpty()) {
                                register()
                            }
                        }
                        AuthentificationType.Phone -> {
                            if (response.code.isNullOrEmpty() && response.phone.isNullOrEmpty()) {
                                register()
                            }
                        }
                    }
                }
            }
            is RegistrationResponseSchema -> {
                viewModelScope.launch {
                    if (response.isSuccessful) {
                        authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                        _state.tryEmit(_state.value.copy(isAuthorised = true))
                    } else {
                        when (_state.value.authentificationType) {
                            AuthentificationType.EMail -> {
                                if (response.email.isNullOrEmpty() && response.password.isNullOrEmpty()) {
                                    authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                                    _state.tryEmit(_state.value.copy(isAuthorised = true))
                                }
                            }
                            AuthentificationType.Phone -> {
                                if (response.phone.isNullOrEmpty() && response.password.isNullOrEmpty()) {
                                    authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                                    _state.tryEmit(_state.value.copy(isAuthorised = true))
                                }
                            }
                        }
                    }
                }
            }
            is UserResponseSchema -> {
                val b = 0
            }
            is SendCodeResponseSchema -> {
                when (_state.value.authentificationType) {
                    AuthentificationType.EMail -> {
                        val newState = _state.value.copy(
                            email = userLoginInfo?.email,
                            phoneErrors = response.phone ?: "",
                        )
                        _state.tryEmit(newState)
                    }
                    AuthentificationType.Phone -> {
                        val newState = _state.value.copy(
                            phone = userLoginInfo?.phone,
                            phoneErrors = response.phone ?: "",
                        )
                        _state.tryEmit(newState)
                    }
                }
            }
            is LoginResponseSchema -> {
                viewModelScope.launch {
                    authenticationStorageRepository.setLoginEmail(response.email)
                    authenticationStorageRepository.setToken(response.token)
                    authenticationStorageRepository.setUser(response.user)
                    authenticationStorageRepository.setStatus(ClientStatus.LOGGED_IN)
                }
            }
            is DefaultHttpErrorSchema -> {
                val newState = _state.value.copy(
                    emailErrors = response.email ?: "",
                    passwordErrors = response.password ?: "",
                    phoneErrors = response.phone ?: ""
                )

                _state.tryEmit(newState)

                when (_state.value.authentificationType) {
                    AuthentificationType.EMail -> {
                        if (response.email.isNullOrEmpty() && response.password.isNullOrEmpty()) {
                            viewModelScope.launch {
                                //login()
                            }
                        }
                    }
                    AuthentificationType.Phone -> {
                        if (response.phone.isNullOrEmpty() && response.password.isNullOrEmpty()) {
                            viewModelScope.launch {
                                //login()
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

    private fun register() {
        viewModelScope.launch {
            userLoginInfo?.let { logInfo ->
                val request = RegistrationRequestSchema(
                    email = logInfo.email,
                    phone = logInfo.phone,
                    password = logInfo.password,
                    code = logInfo.verificationCode,
                    currency = "EUR"
                )
                loginController.registerNewUser(request)
            }
        }
    }

    fun startVerificationCodeRequestTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _resendTimeout.tryEmit("0:${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                _resendTimeout.tryEmit("")
            }
        }.apply { start() }
    }

    private fun setEmptyErrorState() {
        val newState = _state.value.copy(
            emailErrors = "",
            passwordErrors = "",
            phoneErrors = "",
        )

        _state.tryEmit(
            newState
        )
    }

    private fun String.onlyLetters() = all { it.isLetter() }

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
        val emailErrors: String,

        /**
         * User phone
         */
        val phone: String?,

        /**
         * Phone error descriptions list
         */
        val phoneErrors: String,

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
        val passwordErrors: String,

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
         * User news and offers accepted
         */
        val newsAndOffersCheckedState: Boolean,

        /**
         * If the all required fields correctly filled
         */
        val isFieldsCorrect: Boolean,

        /**
         * Need to go to verification screen
         */
        val goToVerificationScreen: Boolean,

        /**
         * Login fields verification state
         */
        val verificationCodeRequested: Boolean,

        /**
         * Password requirements label text
         */
        var passwordRequirementsState: Int,

        /**
         * The type of the user password verification type
         */
        var authentificationType: AuthentificationType,

        /**
         * The type of the user password verification type
         */
        var resendCodeTimeOut: String,
    )
}

private val InitialState = AuthorizationViewModel.State(
    isLoading = false,
    isAuthorised = false,
    email = "",
    emailErrors = "",
    phone = null,
    phoneErrors = "",
    selectedCountry = defaultCountryData,
    password = "",
    passwordErrors = "",
    promoCodeText = "",
    verificationCode = "",
    verificationCodeError = "",
    privacyPolicyCheckedState = false,
    newsAndOffersCheckedState = false,
    isFieldsCorrect = false,
    goToVerificationScreen = false,
    verificationCodeRequested = false,
    passwordRequirementsState = 0,
    AuthentificationType.EMail,
    resendCodeTimeOut = "0:60"
)

sealed class AuthentificationType {
    object EMail : AuthentificationType()
    object Phone : AuthentificationType()
}