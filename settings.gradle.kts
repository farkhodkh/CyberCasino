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
include(":feature-auth")
//include(":feature-auth-api")
include(":ui")
//include(":service-network")
//include(":service-rest-transport")
