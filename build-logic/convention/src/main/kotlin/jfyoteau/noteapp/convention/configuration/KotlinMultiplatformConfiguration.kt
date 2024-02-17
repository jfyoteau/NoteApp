package jfyoteau.noteapp.convention.configuration

import jfyoteau.noteapp.convention.AndroidBuildConfig
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun configureKotlinMultiplatform(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension
) {
    kotlinMultiplatformExtension.apply {
        // Android
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = AndroidBuildConfig.jvmTarget.toString()
                }
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
