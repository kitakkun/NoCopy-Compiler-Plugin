@file:Suppress("UNUSED_VARIABLE")

data class PublicA(val a: Int)

data class A private constructor(val a: Int) {
    companion object {
        fun create(a: Int) = A(a)
    }
}

fun main() {
    // This is valid because the constructor is public.
    val publicA = PublicA(10)
    val publicB = publicA.copy(a = 20)

    // This is invalid because the constructor is private.
    // (will be detected by FIR Checker)
    val a = A.create(10)
    val b = a.copy(a = 20) // if the nocopy compiler plugin is enabled, this line will be reported as an error.
}