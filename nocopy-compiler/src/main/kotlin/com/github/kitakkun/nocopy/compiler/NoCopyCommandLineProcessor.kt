package com.github.kitakkun.nocopy.compiler

import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

@OptIn(ExperimentalCompilerApi::class)
class NoCopyCommandLineProcessor : CommandLineProcessor {
    companion object {
        val ENABLED_KEY = CompilerConfigurationKey<Boolean>("enabled")
    }

    override val pluginId: String = "com.github.kitakkun.nocopy.compiler"

    override val pluginOptions: Collection<AbstractCliOption> = listOf(
        CliOption(
            optionName = "enabled",
            valueDescription = "true|false",
            description = "whether the compiler plugin is enabled or not.",
            required = false,
        )
    )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        when (option.optionName) {
            "enabled" -> configuration.put(ENABLED_KEY, value.toBoolean())
            else -> error("Unexpected compiler option: ${option.optionName}")
        }
    }
}