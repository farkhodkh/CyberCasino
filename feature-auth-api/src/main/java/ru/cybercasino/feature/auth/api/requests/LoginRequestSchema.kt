package ru.cybercasino.feature.auth.api.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestSchema(
    val username: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val password: String
)