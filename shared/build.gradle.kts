plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.ktor.core)
            implementation(libs.ktor.content)
            implementation(libs.ktor.auth)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.logging)
            api(libs.moko.mvvm.core)
            api(libs.moko.mvvm.flow)
        }
        androidMain.dependencies {
            api(libs.koin.android)
            implementation(libs.ktor.okhttp)
        }

        val iosX64Main by getting {
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        val iosArm64Main by getting {
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.ktor.test)
            implementation(libs.ktor.mock)
        }
    }
}

android {
    namespace = "com.example.movieskmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}