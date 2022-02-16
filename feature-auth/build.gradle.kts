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
    implementation(libs.androidx.core)
    implementation(libs.koin.compose)
    implementation(libs.datastore.preferences)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.gson)

    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.accompanist.insets)
    api(libs.accompanist.insetsUi)
    api(libs.accompanist.pager)
    api(libs.accompanist.pager.indicators)

    implementation(project(":ui"))
    implementation(project(":feature-auth-api"))
    implementation(project(":service-network"))
    kapt(libs.moshiCodeGen)
}