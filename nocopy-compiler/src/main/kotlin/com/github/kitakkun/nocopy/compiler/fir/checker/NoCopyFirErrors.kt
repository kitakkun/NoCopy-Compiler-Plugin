package com.github.kitakkun.nocopy.compiler.fir.checker

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.error1
import org.jetbrains.kotlin.diagnostics.rendering.RootDiagnosticRendererFactory

object NoCopyFirErrors {
    val COPY_CALL_FOR_DATA_CLASS_WITH_PRIVATE_CONSTRUCTOR by error1<PsiElement, String>()

    init {
        RootDiagnosticRendererFactory.registerFactory(NoCopyErrorMessages)
    }
}