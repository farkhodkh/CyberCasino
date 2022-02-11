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
    api(libs.kotlinCoroutines)

    api(libs.koin.android)

    api(libs.retrofit)
    api(libs.retrofitConverter)
    implementation(libs.retrofitLoggingInterceptor)

    api(libs.moshi)
    kapt(libs.moshiCodeGen)
}