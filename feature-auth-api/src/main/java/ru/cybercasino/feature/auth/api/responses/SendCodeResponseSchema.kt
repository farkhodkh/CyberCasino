package ru.cybercasino.feature.auth.api.responses

import com.squareup.moshi.JsonClass
import ru.cybercasino.core.network.common.ResponseSchema

@JsonClass(generateAdapter = true)
data class SendCodeResponseSchema(
    override var isSuccessful: Boolean = false,
    val email: String? = null,
    val phone: String? = null,
    val reset: Boolean? = false
): ResponseSchema
