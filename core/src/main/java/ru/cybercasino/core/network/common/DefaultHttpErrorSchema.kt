package ru.cybercasino.core.network.common

import ru.cybercasino.core.network.common.ResponseSchema

abstract class DefaultHttpErrorSchema(
    var email: List<String>? = null,
    var phone: List<String>?= null,
    var password: List<String>?= null,
) : ResponseSchema

class DefaultHttpErrorSchemaBuilder() : DefaultHttpErrorSchema() {

    fun setEmail(email: List<String>) : DefaultHttpErrorSchema {
        this.email = email.toList()
        return this
    }

    fun setPhone(phone: List<String>) : DefaultHttpErrorSchema {
        this.phone = phone
        return this
    }

    fun setPassword(password: List<String>) : DefaultHttpErrorSchema {
        this.password = password
        return this
    }
}