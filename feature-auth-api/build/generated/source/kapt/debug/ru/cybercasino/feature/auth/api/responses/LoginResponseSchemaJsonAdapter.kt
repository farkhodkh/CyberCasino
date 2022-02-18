// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package ru.cybercasino.feature.auth.api.responses

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class LoginResponseSchemaJsonAdapter(
  moshi: Moshi
) : JsonAdapter<LoginResponseSchema>() {
  private val options: JsonReader.Options = JsonReader.Options.of("token", "user", "email", "phone",
      "password")

  private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(String::class.java,
      emptySet(), "token")

  private val nullableUserResponseSchemaAdapter: JsonAdapter<UserResponseSchema?> =
      moshi.adapter(UserResponseSchema::class.java, emptySet(), "user")

  public override fun toString(): String = buildString(41) {
      append("GeneratedJsonAdapter(").append("LoginResponseSchema").append(')') }

  public override fun fromJson(reader: JsonReader): LoginResponseSchema {
    var token: String? = null
    var user: UserResponseSchema? = null
    var email: String? = null
    var phone: String? = null
    var password: String? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> token = nullableStringAdapter.fromJson(reader)
        1 -> user = nullableUserResponseSchemaAdapter.fromJson(reader)
        2 -> email = nullableStringAdapter.fromJson(reader)
        3 -> phone = nullableStringAdapter.fromJson(reader)
        4 -> password = nullableStringAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return LoginResponseSchema(
        token = token,
        user = user,
        email = email,
        phone = phone,
        password = password
    )
  }

  public override fun toJson(writer: JsonWriter, value_: LoginResponseSchema?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("token")
    nullableStringAdapter.toJson(writer, value_.token)
    writer.name("user")
    nullableUserResponseSchemaAdapter.toJson(writer, value_.user)
    writer.name("email")
    nullableStringAdapter.toJson(writer, value_.email)
    writer.name("phone")
    nullableStringAdapter.toJson(writer, value_.phone)
    writer.name("password")
    nullableStringAdapter.toJson(writer, value_.password)
    writer.endObject()
  }
}
