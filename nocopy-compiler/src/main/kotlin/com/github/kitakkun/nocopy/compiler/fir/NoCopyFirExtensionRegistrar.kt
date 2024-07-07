package com.github.kitakkun.nocopy.compiler.fir

import com.github.kitakkun.nocopy.compiler.fir.checker.NoCopyFirAdditionalCheckersExtension
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class NoCopyFirExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        // add factories of FirExtensions by using the special syntax
        +::NoCopyFirAdditionalCheckersExtension
    }
}
