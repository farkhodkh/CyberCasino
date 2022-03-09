package ru.cybercasino.feature.auth.api.responses

import com.squareup.moshi.JsonClass
import ru.cybercasino.core.network.common.ResponseSchema

@JsonClass(generateAdapter = true)
data class CheckCodeResponseSchema(
    override var isSuccessful: Boolean = false,
    val code: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val reset: Boolean? = false,
): ResponseSchema