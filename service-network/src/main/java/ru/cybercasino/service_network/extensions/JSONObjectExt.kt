package ru.cybercasino.service_network.extensions

import org.json.JSONArray
import org.json.JSONObject
import ru.cybercasino.core.network.common.DefaultHttpErrorSchemaBuilder
import ru.cybercasino.core.network.common.ResponseSchema

fun JSONObject.parseDefaultHttpError() : ResponseSchema {
    val jObject = this

    val response = DefaultHttpErrorSchemaBuilder()

    if (jObject.has("email")) {
        val jsonArray = jObject.get("email") as JSONArray
        val list = getListFromArray(jsonArray)
        response.setEmail(list)
    }

    if (jObject.has("phone")) {
        val jsonArray = jObject.get("phone") as JSONArray
        val list = getListFromArray(jsonArray)
        response.setPhone(list)
    }

    if (jObject.has("password")) {
        val jsonArray = jObject.get("password") as JSONArray
        val list = getListFromArray(jsonArray)
        response.setPassword(list)
    }

    return response
}

private fun getListFromArray(jsonArray: JSONArray) : List<String> {
    val listData = mutableListOf<String>()

    for (i in 0 until jsonArray.length()) {
        listData.add(jsonArray.getString(i))
    }

    return listData
}