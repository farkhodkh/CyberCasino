package ru.cybercasino.feature.auth.api.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendCodeRequestSchema(
    val email: String? = null,
    val phone: String? = null,
    val reset: Boolean = false
)
