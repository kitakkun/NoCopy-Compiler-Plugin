package com.github.kitakkun.nocopy.compiler

import com.github.kitakkun.nocopy.compiler.fir.NoCopyFirExtensionRegistrar
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter

@OptIn(ExperimentalCompilerApi::class)
class NoCopyCompilerPluginRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean = true

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        val enabled = configuration.get(NoCopyCommandLineProcessor.ENABLED_KEY, true)
        if (!enabled) return
        FirExtensionRegistrarAdapter.registerExtension(NoCopyFirExtensionRegistrar())
    }
}
