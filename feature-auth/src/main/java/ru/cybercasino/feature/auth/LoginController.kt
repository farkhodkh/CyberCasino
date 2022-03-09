package ru.cybercasino.feature.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.cybercasino.core.network.common.ResponseSchema
import ru.cybercasino.feature.auth.api.requests.*
import ru.cybercasino.feature.auth_api.AuthenticationApi
import ru.cybercasino.service_network.extensions.httpErrorHandler

class LoginController(
    private val authenticationRepository: AuthenticationApi
) {
    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    suspend fun login(request: LoginRequestSchema) {
        val responseSchema = authenticationRepository
            .login(request)
            .httpErrorHandler()

        _loginState.tryEmit(LoginState(response = responseSchema))
    }

    suspend fun registerNewUser(request: RegistrationRequestSchema) {
        val responseSchema = authenticationRepository
            .register(request)
            .httpErrorHandler()

        _loginState.tryEmit(LoginState(response = responseSchema))
    }

    suspend fun validateNewUser(request: UserValidationRequestSchema) {
        val responseSchema = authenticationRepository
            .validateUser(request)
            .httpErrorHandler()

        _loginState.tryEmit(LoginState(response = responseSchema))
    }

    suspend fun sendCode(request: SendCodeRequestSchema) {
        val responseSchema = authenticationRepository
            .sendCode(request)
            .httpErrorHandler()

        _loginState.tryEmit(LoginState(response = responseSchema))
    }

    suspend fun checkCode(request: CheckCodeRequestSchema) {
        val responseSchema = authenticationRepository
            .checkCode(request)
            .httpErrorHandler()

        _loginState.tryEmit(LoginState(response = responseSchema))
    }

    class LoginState(val response: ResponseSchema? = null)
}