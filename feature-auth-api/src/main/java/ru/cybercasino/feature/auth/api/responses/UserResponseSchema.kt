package ru.cybercasino.feature.auth.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.cybercasino.core.network.common.ResponseSchema

@JsonClass(generateAdapter = true)
data class UserResponseSchema(
    override var isSuccessful: Boolean = false,
    val pk: Int? = null,
    val username: String? = null,
    val email: String? = null,
    @Json(name = "first_name")
    val firstName: String? = null,
    @Json(name = "last_name")
    val lastName: String? = null,
    val domain: String? = null,
): ResponseSchema