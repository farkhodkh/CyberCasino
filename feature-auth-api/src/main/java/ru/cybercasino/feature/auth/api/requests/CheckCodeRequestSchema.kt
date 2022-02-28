package ru.cybercasino.feature.auth.api.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckCodeRequestSchema(
    val code: String,
    val phone: String? = null,
    val email: String? = null,
    val reset: Boolean
)
