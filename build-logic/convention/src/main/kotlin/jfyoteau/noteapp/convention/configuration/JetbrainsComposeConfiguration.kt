package jfyoteau.noteapp.convention.configuration

import jfyoteau.noteapp.convention.extensions.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureJetbrainsCompose(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension
) {
    val composeExtension = extensions.getByType<ComposeExtension>()
    composeExtension.apply {
        kotlinCompilerPlugin.set(libs.findVersion("jetbrains.compose.compiler").get().toString())
    }
    val compose = composeExtension.dependencies

    kotlinMultiplatformExtension.apply {
        sourceSets.getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
            }
        }

        sourceSets.getByName("androidMain") {
            dependencies {
                implementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            }
        }

        sourceSets.getByName("desktopMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
