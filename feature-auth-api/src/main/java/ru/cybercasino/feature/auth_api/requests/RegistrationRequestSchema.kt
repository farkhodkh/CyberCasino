package ru.cybercasino.feature.auth_api.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequestSchema(
    val email: String,
    val phone: String,
    val password: String,
    val code: String,
    val currency: String
)