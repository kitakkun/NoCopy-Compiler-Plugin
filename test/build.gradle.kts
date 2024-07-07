import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions.freeCompilerArgs.addAll(
        "-P",
        // try replacing 'true' with 'false'. Then it will compile without any errors.
        "plugin:com.github.kitakkun.nocopy.compiler:enabled=true",
    )
}

dependencies {
    kotlinCompilerPluginClasspath(project(":nocopy-compiler"))
}
