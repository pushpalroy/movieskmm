import io.gitlab.arturbosch.detekt.Detekt

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinCocoapods) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.kmpNativeCoroutines) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt)
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        // Define the detekt configuration(s) you want to use.
        // Defaults to the default detekt configuration.
        config = files("${rootProject.rootDir}/config/detekt/detekt.yml")

        // Applies the config files on top of detekt's default config file. `false` by default.
        buildUponDefaultConfig = true

        // Builds the AST in parallel. Rules are always executed in parallel.
        // Can lead to speedups in larger projects. `false` by default.
        parallel = true

        // Turns on all the rules. `false` by default.
        allRules = false

        // Adds debug output during task execution. `false` by default.
        debug = false
    }
    tasks.withType<Detekt>().configureEach {
        reports {
            // Enable/Disable HTML report (default: true)
            html.required.set(true)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}