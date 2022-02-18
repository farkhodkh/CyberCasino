// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package ru.cybercasino.feature.auth.api.requests

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import java.lang.reflect.Constructor
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.jvm.Volatile
import kotlin.text.buildString

public class CheckCodeRequestSchemaJsonAdapter(
  moshi: Moshi
) : JsonAdapter<CheckCodeRequestSchema>() {
  private val options: JsonReader.Options = JsonReader.Options.of("code", "phone", "email", "reset")

  private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java, emptySet(),
      "code")

  private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(String::class.java,
      emptySet(), "phone")

  private val booleanAdapter: JsonAdapter<Boolean> = moshi.adapter(Boolean::class.java, emptySet(),
      "reset")

  @Volatile
  private var constructorRef: Constructor<CheckCodeRequestSchema>? = null

  public override fun toString(): String = buildString(44) {
      append("GeneratedJsonAdapter(").append("CheckCodeRequestSchema").append(')') }

  public override fun fromJson(reader: JsonReader): CheckCodeRequestSchema {
    var code: String? = null
    var phone: String? = null
    var email: String? = null
    var reset: Boolean? = null
    var mask0 = -1
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> code = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("code", "code",
            reader)
        1 -> {
          phone = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 1).inv()
          mask0 = mask0 and 0xfffffffd.toInt()
        }
        2 -> {
          email = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 2).inv()
          mask0 = mask0 and 0xfffffffb.toInt()
        }
        3 -> reset = booleanAdapter.fromJson(reader) ?: throw Util.unexpectedNull("reset", "reset",
            reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    if (mask0 == 0xfffffff9.toInt()) {
      // All parameters with defaults are set, invoke the constructor directly
      return  CheckCodeRequestSchema(
          code = code ?: throw Util.missingProperty("code", "code", reader),
          phone = phone,
          email = email,
          reset = reset ?: throw Util.missingProperty("reset", "reset", reader)
      )
    } else {
      // Reflectively invoke the synthetic defaults constructor
      @Suppress("UNCHECKED_CAST")
      val localConstructor: Constructor<CheckCodeRequestSchema> = this.constructorRef ?:
          CheckCodeRequestSchema::class.java.getDeclaredConstructor(String::class.java,
          String::class.java, String::class.java, Boolean::class.javaPrimitiveType,
          Int::class.javaPrimitiveType, Util.DEFAULT_CONSTRUCTOR_MARKER).also {
          this.constructorRef = it }
      return localConstructor.newInstance(
          code ?: throw Util.missingProperty("code", "code", reader),
          phone,
          email,
          reset ?: throw Util.missingProperty("reset", "reset", reader),
          mask0,
          /* DefaultConstructorMarker */ null
      )
    }
  }

  public override fun toJson(writer: JsonWriter, value_: CheckCodeRequestSchema?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("code")
    stringAdapter.toJson(writer, value_.code)
    writer.name("phone")
    nullableStringAdapter.toJson(writer, value_.phone)
    writer.name("email")
    nullableStringAdapter.toJson(writer, value_.email)
    writer.name("reset")
    booleanAdapter.toJson(writer, value_.reset)
    writer.endObject()
  }
}
