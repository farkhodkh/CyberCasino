package ru.cybercasino.feature_auth_api.responces

import com.squareup.moshi.JsonClass

/**
 * The authentication request schema
 */
@JsonClass(generateAdapter = true)
class AuthenticationRequestSchema(
    val username: String,
    val email: String,
    val password: String,
    val phone: String
)