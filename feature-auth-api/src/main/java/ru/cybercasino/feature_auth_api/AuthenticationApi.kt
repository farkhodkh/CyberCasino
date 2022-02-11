package ru.cybercasino.feature_auth_api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.cybercasino.feature_auth_api.requests.AuthenticationResponceSchema
import ru.cybercasino.feature_auth_api.responces.AuthenticationRequestSchema

/**
 * User auth API.
 */
interface AuthenticationApi {

    /**
     * Generates random UUID to use as client UUID.
     *
     * @return The [ClientUuidDto] with UUID.
     */
    @POST("auth/login/")
    suspend fun login(
        @Body body: AuthenticationRequestSchema
    ): AuthenticationResponceSchema
}