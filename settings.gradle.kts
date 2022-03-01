pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CyberCasino"
include(":app")
include(":core")
include(":ui")
include(":feature-auth")
include(":feature-auth-api")
//include(":feature-user-profile")
//include(":feature-user-profile-api")
include(":service-network")
include(":feature-main-profile")
//include(":feature-main-profile-api")
