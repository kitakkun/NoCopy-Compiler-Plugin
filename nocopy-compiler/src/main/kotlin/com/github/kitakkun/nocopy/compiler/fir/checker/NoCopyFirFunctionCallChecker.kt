package com.github.kitakkun.nocopy.compiler.fir.checker

import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.diagnostics.reportOn
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.primaryConstructorSymbol
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirFunctionCallChecker
import org.jetbrains.kotlin.fir.declarations.utils.isData
import org.jetbrains.kotlin.fir.declarations.utils.visibility
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.types.resolvedType
import org.jetbrains.kotlin.fir.types.toRegularClassSymbol
import org.jetbrains.kotlin.name.Name

object NoCopyFirFunctionCallChecker : FirFunctionCallChecker(MppCheckerKind.Common) {
    override fun check(
        expression: FirFunctionCall,
        context: CheckerContext,
        reporter: DiagnosticReporter,
    ) {
        if (expression.calleeReference.name != Name.identifier("copy")) return
        val classSymbol = expression.resolvedType.toRegularClassSymbol(context.session) ?: return
        if (!classSymbol.isData) return
        val constructorVisibility = classSymbol.primaryConstructorSymbol(context.session)?.visibility ?: return
        if (constructorVisibility != Visibilities.Private) return

        reporter.reportOn(
            source = expression.calleeReference.source,
            context = context,
            factory = NoCopyFirErrors.COPY_CALL_FOR_DATA_CLASS_WITH_PRIVATE_CONSTRUCTOR,
            a = classSymbol.classId.asString(),
        )
    }
}
