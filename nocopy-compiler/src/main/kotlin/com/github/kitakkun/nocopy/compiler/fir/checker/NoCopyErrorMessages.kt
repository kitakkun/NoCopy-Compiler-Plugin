package com.github.kitakkun.nocopy.compiler.fir.checker

import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers.TO_STRING
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory

object NoCopyErrorMessages : BaseDiagnosticRendererFactory() {
    override val MAP: KtDiagnosticFactoryToRendererMap = KtDiagnosticFactoryToRendererMap("NoCopy").apply {
        put(
            NoCopyFirErrors.COPY_CALL_FOR_DATA_CLASS_WITH_PRIVATE_CONSTRUCTOR,
            "data class {0} has a private constructor. The use of copy method is not allowed",
            rendererA = TO_STRING,
        )
    }
}