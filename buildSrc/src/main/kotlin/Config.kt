import org.gradle.api.JavaVersion

/**
 * Project configuration properties.
 */
object Config {
    const val APPLICATION_ID = "ru.cybercasino.android"
    const val TARGET_SDK = 31
    const val COMPILE_SDK = 31
    const val MIN_SDK = 21

    val JAVA_VERSION = JavaVersion.VERSION_11

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
}