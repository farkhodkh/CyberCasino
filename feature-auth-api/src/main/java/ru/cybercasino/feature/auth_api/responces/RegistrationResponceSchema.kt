package ru.cybercasino.feature.auth_api.responces

import com.squareup.moshi.JsonClass

//{
//    "token": "string",
//    "user": {
//    "pk": 0,
//    "username": "string",
//    "email": "string",
//    "first_name": "string",
//    "last_name": "string",
//    "domain": "string"
//}
//}
@JsonClass(generateAdapter = true)
data class RegistrationResponceSchema(
    val token: String,
    val user: UserResponceSchema,
)