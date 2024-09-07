package jfyoteau.noteapp.convention

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object AndroidBuildConfig {
    const val COMPILE_SDK_VERSION = 34
    const val MIN_SDK_VERSION = 29
    const val TARGET_SDK_VERSION = 34
    val jvmTarget = JvmTarget.JVM_17
    val javaVersion = JavaVersion.VERSION_17
}
