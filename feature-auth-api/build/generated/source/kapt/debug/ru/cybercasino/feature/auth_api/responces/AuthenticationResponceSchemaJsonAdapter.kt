// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package ru.cybercasino.feature.auth_api.responces

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class AuthenticationResponceSchemaJsonAdapter(
  moshi: Moshi
) : JsonAdapter<AuthenticationResponceSchema>() {
  private val options: JsonReader.Options = JsonReader.Options.of("token", "user")

  private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java, emptySet(),
      "token")

  private val userResponceSchemaAdapter: JsonAdapter<UserResponceSchema> =
      moshi.adapter(UserResponceSchema::class.java, emptySet(), "user")

  public override fun toString(): String = buildString(50) {
      append("GeneratedJsonAdapter(").append("AuthenticationResponceSchema").append(')') }

  public override fun fromJson(reader: JsonReader): AuthenticationResponceSchema {
    var token: String? = null
    var user: UserResponceSchema? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> token = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("token", "token",
            reader)
        1 -> user = userResponceSchemaAdapter.fromJson(reader) ?: throw Util.unexpectedNull("user",
            "user", reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return AuthenticationResponceSchema(
        token = token ?: throw Util.missingProperty("token", "token", reader),
        user = user ?: throw Util.missingProperty("user", "user", reader)
    )
  }

  public override fun toJson(writer: JsonWriter, value_: AuthenticationResponceSchema?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("token")
    stringAdapter.toJson(writer, value_.token)
    writer.name("user")
    userResponceSchemaAdapter.toJson(writer, value_.user)
    writer.endObject()
  }
}
