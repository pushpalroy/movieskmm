plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "network"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.ktor.core)
                implementation(libs.ktor.content)
                implementation(libs.ktor.auth)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.logging)
                implementation(project(":domain"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.ktor.test)
                implementation(libs.ktor.mock)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }

        val iosX64Main by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        val iosArm64Main by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }
    }
}

android {
    namespace = "com.example.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
