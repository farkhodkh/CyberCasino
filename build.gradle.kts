// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

    }
}

plugins {
    id ("com.android.application") version "7.1.0" apply false
    id ("com.android.library") version "7.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.5.31" apply false
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        reports {
            html.enabled = true
            xml.enabled = true
        }
    }
}