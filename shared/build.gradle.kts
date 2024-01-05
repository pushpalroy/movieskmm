import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kmpNativeCoroutines)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Common library for the MoviesKMM app"
        homepage = "https://github.com/pushpalroy/movieskmm"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        pod("SQLCipher", libs.versions.iosSqlCipher.get())
        framework {
            baseName = "MultiPlatformLibrary"
            binaryOption("bundleId", "com.example.movieskmm.MultiPlatformLibrary")
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.ktor.core)
                implementation(libs.ktor.content)
                implementation(libs.ktor.auth)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.logging)
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqlDelight.coroutinesExt)
                implementation(libs.stately.common)
                api(libs.kmm.viewmodel.core)
                api(libs.logging.napier)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.koin.android)
                api(libs.commonsware.saferoom)
                implementation(libs.ktor.okhttp)
                implementation(libs.sqldelight.android.driver)
            }
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
                implementation(libs.sqldelight.native.driver)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

val localProperties = Properties()
localProperties.load(rootProject.file("local.properties").reader())

buildkonfig {
    packageName = "com.example.movieskmm"
    val props = Properties()
    try {
        props.load(file("../local.properties").inputStream())
    } catch (e: Exception) {
    }

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "API_READ_ACCESS_TOKEN",
            props["api_read_access_token"]?.toString() ?: "abc"
        )
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.movieskmm.data.local.db")
        }
        linkSqlite = false
    }
}
