package ru.cybercasino.feature.auth_api.responces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserResponceSchema(
    val pk: Int,
    val username: String,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val domain: String,
)