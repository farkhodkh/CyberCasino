// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        @Suppress("UnstableApiUsage") // VersionCatalogsExtension has @Incubating API
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs") as org.gradle.accessors.dm.LibrariesForLibs
        classpath(libs.plugin.gradleTools)
        classpath(libs.plugin.kotlinGradle)
    }
}

plugins {
    id ("com.android.application") version "7.1.0" apply false
    id ("com.android.library") version "7.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("com.diffplug.spotless") version "5.14.2"
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        reports {
            html.enabled = true
            xml.enabled = true
        }
    }

    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            ktlint("0.42.1").userData(
                kotlin.collections.mapOf(
                    // Disable missing new line warning
                    "disabled_rules" to "final-newline"
                )
            )
        }
    }
}