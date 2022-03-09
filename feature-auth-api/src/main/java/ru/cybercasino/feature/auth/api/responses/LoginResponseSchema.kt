package ru.cybercasino.feature.auth.api.responses

import com.squareup.moshi.JsonClass
import ru.cybercasino.core.network.common.ResponseSchema

@JsonClass(generateAdapter = true)
data class LoginResponseSchema(
    override var isSuccessful: Boolean = false,
    val token: String?,
    val user: UserResponseSchema?,
    val email: String?,
    val phone: String?,
    val password: String?,
): ResponseSchema