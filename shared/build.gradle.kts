plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig") version "0.15.1"
}

buildkonfig {
    packageName = "com.example.movieskmm"

    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "API_READ_ACCESS_TOKEN", ((project.findProperty("api_read_access_token") ?: "") as String))
    }
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
            baseName = "MultiPlatformLibrary"
            isStatic = false
            export(libs.moko.mvvm.core)
            export(libs.moko.mvvm.flow)
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
                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.flow)
            }
        }
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.moko.mvvm.flow.compose)
            implementation(libs.ktor.okhttp)
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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