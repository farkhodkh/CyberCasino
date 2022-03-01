package ru.cybercasino.feature.auth.api

import kotlinx.coroutines.flow.Flow
import ru.cybercasino.feature.auth.ClientStatus

interface AuthenticationStorageRepository {
    /**
     * Sets the [ClientStatus].
     *
     * @param value The new value.
     * @see ClientStatus
     */
    suspend fun setStatus(value: ClientStatus)

    /**
     * Gets the flow with [ClientStatus].
     *
     * @see ClientStatus
     */
    suspend fun getStatus(): Flow<ClientStatus>

    /**
     * Sets the client access token.
     *
     * @param value The new value.
     */
    suspend fun setToken(value: String?)

    /**
     * Gets the flow with client access token.
     */
    suspend fun getToken(): Flow<String?>

    /**
     * Sets the e-mail address the client used during login.
     *
     * @param value The new value.
     */
    suspend fun setLoginEmail(value: String?)

    /**
     * Gets the flow with e-mail address the client used during login.
     */
    suspend fun getLoginEmail(): Flow<String?>

    /**
     * Sets the phone the client used during login.
     *
     * @param value The new value.
     */
    suspend fun setLoginPhone(value: String?)

    /**
     * Gets the flow with phone the client used during login.
     */
    suspend fun getLoginPhone(): Flow<String?>

    /**
     * Sets the name the client used during login.
     *
     * @param value The new value.
     */
    suspend fun setLoginName(value: String?)

    /**
     * Gets the flow with name the client used during login.
     */
    suspend fun getLoginName(): Flow<String?>

    /**
     * Sets the pass
     *
     * @param value The new value.
     */
    suspend fun setPass(value: String?)

    /**
     * Gets the pass.
     */
    suspend fun getPass(): Flow<String?>

    /**
     * Sets the pass
     *
     * @param value The new value.
     */
    suspend fun setVerificationCode(value: String?)

    /**
     * Gets the pass.
     */
    suspend fun getVerificationCode(): Flow<String?>

    /**
     * Sets the client access token.
     *
     * @param value The new value.
     */
    suspend fun setUser(value: UserResponseSchema?)

    /**
     * Gets the flow with client access token.
     */
    suspend fun getUser(): Flow<UserResponseSchema?>

    /**
     * Gets login info
     */
    suspend fun getLoginInfo(): Flow<LoginInfo?>
}