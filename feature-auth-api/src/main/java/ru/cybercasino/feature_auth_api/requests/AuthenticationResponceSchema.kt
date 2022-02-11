package ru.cybercasino.feature_auth_api.requests

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AuthenticationResponceSchema(
    val token: String,
    val user: User
)

class User(
    val pk: Int,
    val username: String,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val domain: String
)