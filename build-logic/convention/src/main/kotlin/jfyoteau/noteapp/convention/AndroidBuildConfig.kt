package jfyoteau.noteapp.convention

import org.gradle.api.JavaVersion

object AndroidBuildConfig {
    const val compileSdkVersion = 34
    const val minSdkVersion = 29
    const val targetSdkVersion = 34
    val jvmTarget = JavaVersion.VERSION_17
}
