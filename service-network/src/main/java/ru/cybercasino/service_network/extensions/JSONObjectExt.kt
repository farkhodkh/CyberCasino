package ru.cybercasino.service_network.extensions

import com.google.gson.JsonObject
import java.lang.StringBuilder
import org.json.JSONArray
import org.json.JSONObject
import ru.cybercasino.core.network.common.DefaultHttpErrorSchemaBuilder
import ru.cybercasino.core.network.common.ResponseSchema

fun JSONObject.parseDefaultHttpError() : ResponseSchema {
    val jObject = this

    val response = DefaultHttpErrorSchemaBuilder()

    if (jObject.has("email")) {
        val jsonObject = jObject.get("email")
        val result = getStringFromArray(jsonObject)
        response.setEmail(result)
    }

    if (jObject.has("phone")) {
        val jsonObject = jObject.get("phone")
        val result = getStringFromArray(jsonObject)
        response.setEmail(result)
    }

    if (jObject.has("password")) {
        val jsonObject = jObject.get("password")
        val result = getStringFromArray(jsonObject)
        response.setEmail(result)
    }

    return response
}

private fun getStringFromArray(jsonObject: Any) = when (jsonObject) {
        is JSONArray -> {
            StringBuilder().apply {
                for (i in 0 until jsonObject.length()) {
                    this
                        .append(jsonObject.getString(i))
                }
            }.toString()
        }
        is String -> {
            jsonObject
        }
    else -> {
        ""
    }
}