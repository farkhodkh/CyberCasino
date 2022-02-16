plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxCompose.get()
    }
}

dependencies {
    api(libs.koin.android)
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    api(libs.kotlinCoroutines)
    implementation(libs.datastore.preferences)

    api(libs.moshi)
    kapt(libs.moshiCodeGen)
}