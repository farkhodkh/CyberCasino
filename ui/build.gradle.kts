plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        consumerProguardFile("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility(Config.JAVA_VERSION)
        targetCompatibility(Config.JAVA_VERSION)
    }

    kotlinOptions {
        jvmTarget = Config.JAVA_VERSION.toString()
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxCompose.get()
    }
}

dependencies {

    implementation(libs.accompanist.systemUiController)
    implementation(libs.androidx.material )
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)

    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.accompanist.insets)
    api(libs.accompanist.insetsUi)
    api(libs.accompanist.systemUiController)
}