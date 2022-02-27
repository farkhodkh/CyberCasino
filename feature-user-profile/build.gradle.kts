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
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.compose.material.icons.extended)

//    implementation(libs.androidx.core)

    implementation(project(":ui"))
//    kapt(libs.moshiCodeGen)
}