package ru.cybercasino.feature.auth_api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.cybercasino.feature.auth.api.requests.*
import ru.cybercasino.feature.auth.api.responses.*

interface AuthenticationApi {
    @POST("auth/login/")
    suspend fun login(@Body body: LoginRequestSchema): Response<LoginResponseSchema>

    @POST("auth/registration/")
    suspend fun register(@Body body: RegistrationRequestSchema): Response<RegistrationResponseSchema>

    @POST("auth/validate-user/")
    suspend fun validateUser(@Body body: UserValidationRequestSchema): Response<UserValidationResponseSchema>

    @POST("auth/check-code/")
    suspend fun checkCode(@Body body: CheckCodeRequestSchema): Response<CheckCodeResponseSchema>

    @POST("auth/send-code/")
    suspend fun sendCode(@Body body: SendCodeRequestSchema): Response<SendCodeResponseSchema>
}