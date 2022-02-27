plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxCompose.get()
    }
    compileOptions {
        sourceCompatibility(Config.JAVA_VERSION)
        targetCompatibility(Config.JAVA_VERSION)
    }
}

dependencies {
    api(project(":core"))

    implementation(libs.androidx.activity.compose)
    api(libs.retrofitConverter)
    api(libs.retrofit)
    api(libs.retrofitLoggingInterceptor)
    implementation(libs.gson)
    kapt(libs.moshiCodeGen)
}