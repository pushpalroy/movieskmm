import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetContainer
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
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
//    iosIntermediateSourceSets(iosX64(), iosArm64(), iosSimulatorArm64())
    //applyDefaultHierarchyTemplate()

    cocoapods {
        summary = "Common library for the MoviesKMM app"
        homepage = "https://github.com/pushpalroy/movieskmm"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "MultiPlatformLibrary"
            //binaryOption("bundleId", "com.example.movieskmm.MultiPlatformLibrary")
            isStatic = false
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
                implementation(libs.stately.common)
                api(libs.kmm.viewmodel.core)
                api(libs.logging.napier)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.koin.android)
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
//        iosMain.dependencies {
//            implementation(libs.ktor.darwin)
//            implementation(libs.sqldelight.native.driver)
//        }

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
    }
    //linkSqlite.set(false)
}

//FIXME https://github.com/cashapp/sqldelight/issues/4523
//fun KotlinSourceSetContainer.iosIntermediateSourceSets(vararg iosTargets: KotlinNativeTarget) {
//    val children: List<Pair<KotlinSourceSet, KotlinSourceSet>> = iosTargets.map { target ->
//        val main = target.compilations.getByName(KotlinCompilation.MAIN_COMPILATION_NAME).defaultSourceSet
//        val test = target.compilations.getByName(KotlinCompilation.TEST_COMPILATION_NAME).defaultSourceSet
//        return@map main to test
//    }
//    val parent: Pair<KotlinSourceSet, KotlinSourceSet> = Pair(
//        first = sourceSets.getByName(KotlinSourceSet.COMMON_MAIN_SOURCE_SET_NAME),
//        second = sourceSets.getByName(KotlinSourceSet.COMMON_TEST_SOURCE_SET_NAME)
//    )
//    createIntermediateSourceSet("iosMain", children.map { it.first }, parent.first)
//    createIntermediateSourceSet("iosTest", children.map { it.second }, parent.second)
//}
//
//fun KotlinSourceSetContainer.createIntermediateSourceSet(
//    name: String,
//    children: List<KotlinSourceSet>,
//    parent: KotlinSourceSet
//): KotlinSourceSet = sourceSets.maybeCreate(name).apply {
//    dependsOn(parent)
//    children.forEach { it.dependsOn(this) }
//}
