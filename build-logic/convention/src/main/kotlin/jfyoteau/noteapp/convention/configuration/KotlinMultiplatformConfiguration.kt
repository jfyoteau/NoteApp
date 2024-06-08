package jfyoteau.noteapp.convention.configuration

import jfyoteau.noteapp.convention.AndroidBuildConfig
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun configureKotlinMultiplatform(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension
) {
    kotlinMultiplatformExtension.apply {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(AndroidBuildConfig.javaVersion.majorVersion))
        }

        // https://kotlinlang.org/docs/multiplatform-expect-actual.html#expected-and-actual-classes
        // To suppress this warning about usage of expected and actual classes
        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }

        // Android
        androidTarget {
            compilerOptions {
                jvmTarget.set(AndroidBuildConfig.jvmTarget)
            }
        }

        // Desktop
        jvm("desktop")

        // iOS
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }
}
