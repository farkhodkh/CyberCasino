package ru.cybercasino.feature.auth.api.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequestSchema(
    val email: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val code: String? = null,
    val currency: String
)