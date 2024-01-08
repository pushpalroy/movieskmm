enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://s3.amazonaws.com/repo.commonsware.com")
    }
}

rootProject.name = "MoviesKMM"
include(":androidApp")
include(":shared")
