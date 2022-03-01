package ru.cybercasino.feature.auth.api.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserValidationRequestSchema(
    val email: String? = null,
    val phone: String? = null,
    val reset: Boolean = false,
    val password: String
)