package ru.cybercasino.core.network.common

abstract class DefaultHttpErrorSchema(
    var email: String? = null,
    var phone: String?= null,
    var password: String?= null,
) : ResponseSchema

class DefaultHttpErrorSchemaBuilder() : DefaultHttpErrorSchema() {

    override var isSuccessful: Boolean = false

    fun setEmail(email: String) : DefaultHttpErrorSchema {
        this.email = email
        return this
    }

    fun setPhone(phone: String) : DefaultHttpErrorSchema {
        this.phone = phone
        return this
    }

    fun setPassword(password: String) : DefaultHttpErrorSchema {
        this.password = password
        return this
    }
}