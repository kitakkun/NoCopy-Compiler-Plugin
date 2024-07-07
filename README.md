# NoCopy Compiler Plugin(K2)

This example project demonstrates how to prohibit the usage of the `copy` method on a data class
with a private constructor by extending the K2 Kotlin Compiler.

This repository might be useful as a reference for implementing `FirAdditionalCheckersExtension`.

## Overview

The NoCopy Compiler Plugin (K2) is designed to help developers enforce rules in their Kotlin
codebase. Specifically, it prevents the invocation of the `copy` method on data classes with private
constructors, ensuring that such classes maintain their intended immutability.

```kotlin
data class A private constructor(val a: Int) {
    companion object {
        fun create(a: Int) = A(a)
    }
}

val a = A(10)
val b = a.copy(20) // This line will not be detected as an error before Kotlin 2.0.20-Beta1.
```

## Motivation

Prior to Kotlin 2.0.20-Beta1, there was an issue where instances of data classes with private
constructors could still be created using the `copy` method. This plugin addresses that issue by
providing a way to enforce this restriction at compile time, ensuring that private constructors
maintain their intended level of control over instance creation.

```kotlin
data class A private constructor(val a: Int) {
    companion object {
        fun create(a: Int) = A(a)
    }
}

val a = A(10)
val b = a.copy(20) // The NoCopy Compiler Plugin will report this line as an error! ❌
```

## How to Examine

You can enable or disable the NoCopy compiler plugin by using a Kotlin Compiler option.

In [test/build.gradle.kts](test/build.gradle.kts):

```kotlin
kotlin {
    jvm()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions.freeCompilerArgs.addAll(
        "-P",
        // try replacing 'true' with 'false'. Then it will compile without any errors.
        "plugin:com.github.kitakkun.nocopy.compiler:enabled=true",
    )
}
```

## Additional Resources

- [Speaker Deck](https://speakerdeck.com/kitakkun/after-kotlin-fest-2024-lt-night-at-sansan-motutomotutokotlinwohao-kininaru-k2-compiler-plugindeyou-ndemiyou)

## License

This project is licenced under the MIT license. See the [LICENSE](LICENSE) file for details.
