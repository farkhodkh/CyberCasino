plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(Config.JAVA_VERSION)
        targetCompatibility(Config.JAVA_VERSION)
    }
    kotlinOptions {
        jvmTarget = Config.JAVA_VERSION.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxCompose.get()
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.accompanist.navigationAnimation)
    implementation(libs.accompanist.navigationMaterial)

    api(libs.accompanist.insets)
    api(libs.accompanist.insetsUi)
    api(libs.accompanist.systemUiController)
    implementation(libs.koin.compose)

    implementation(project(":ui"))
    implementation(project(":feature-auth"))
//    implementation(project(":feature-auth-api"))
//    implementation(project(":service-network"))
}