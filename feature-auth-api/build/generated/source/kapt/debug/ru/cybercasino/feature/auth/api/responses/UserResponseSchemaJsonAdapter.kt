// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package ru.cybercasino.feature.auth.api.responses

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import java.lang.reflect.Constructor
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.jvm.Volatile
import kotlin.text.buildString

public class UserResponseSchemaJsonAdapter(
  moshi: Moshi
) : JsonAdapter<UserResponseSchema>() {
  private val options: JsonReader.Options = JsonReader.Options.of("pk", "username", "email",
      "first_name", "last_name", "domain")

  private val nullableIntAdapter: JsonAdapter<Int?> = moshi.adapter(Int::class.javaObjectType,
      emptySet(), "pk")

  private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(String::class.java,
      emptySet(), "username")

  @Volatile
  private var constructorRef: Constructor<UserResponseSchema>? = null

  public override fun toString(): String = buildString(40) {
      append("GeneratedJsonAdapter(").append("UserResponseSchema").append(')') }

  public override fun fromJson(reader: JsonReader): UserResponseSchema {
    var pk: Int? = null
    var username: String? = null
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var domain: String? = null
    var mask0 = -1
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> {
          pk = nullableIntAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 0).inv()
          mask0 = mask0 and 0xfffffffe.toInt()
        }
        1 -> {
          username = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 1).inv()
          mask0 = mask0 and 0xfffffffd.toInt()
        }
        2 -> {
          email = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 2).inv()
          mask0 = mask0 and 0xfffffffb.toInt()
        }
        3 -> {
          firstName = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 3).inv()
          mask0 = mask0 and 0xfffffff7.toInt()
        }
        4 -> {
          lastName = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 4).inv()
          mask0 = mask0 and 0xffffffef.toInt()
        }
        5 -> {
          domain = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 5).inv()
          mask0 = mask0 and 0xffffffdf.toInt()
        }
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    if (mask0 == 0xffffffc0.toInt()) {
      // All parameters with defaults are set, invoke the constructor directly
      return  UserResponseSchema(
          pk = pk,
          username = username,
          email = email,
          firstName = firstName,
          lastName = lastName,
          domain = domain
      )
    } else {
      // Reflectively invoke the synthetic defaults constructor
      @Suppress("UNCHECKED_CAST")
      val localConstructor: Constructor<UserResponseSchema> = this.constructorRef ?:
          UserResponseSchema::class.java.getDeclaredConstructor(Int::class.javaObjectType,
          String::class.java, String::class.java, String::class.java, String::class.java,
          String::class.java, Int::class.javaPrimitiveType, Util.DEFAULT_CONSTRUCTOR_MARKER).also {
          this.constructorRef = it }
      return localConstructor.newInstance(
          pk,
          username,
          email,
          firstName,
          lastName,
          domain,
          mask0,
          /* DefaultConstructorMarker */ null
      )
    }
  }

  public override fun toJson(writer: JsonWriter, value_: UserResponseSchema?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("pk")
    nullableIntAdapter.toJson(writer, value_.pk)
    writer.name("username")
    nullableStringAdapter.toJson(writer, value_.username)
    writer.name("email")
    nullableStringAdapter.toJson(writer, value_.email)
    writer.name("first_name")
    nullableStringAdapter.toJson(writer, value_.firstName)
    writer.name("last_name")
    nullableStringAdapter.toJson(writer, value_.lastName)
    writer.name("domain")
    nullableStringAdapter.toJson(writer, value_.domain)
    writer.endObject()
  }
}
