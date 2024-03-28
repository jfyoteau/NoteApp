package jfyoteau.noteapp.convention.plugin

import jfyoteau.noteapp.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.kotlin.multiplatform.library")
                apply("convention.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.getByName("commonMain") {
                    dependencies {
                        implementation(project(":core:presentation"))
                        implementation(project(":core:resources"))
                        implementation(libs.findLibrary("koin-core").get())
                        implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                        implementation(libs.findLibrary("decompose").get())
                        implementation(libs.findLibrary("decompose-extensions-compose").get())
                        implementation(libs.findLibrary("kotlinx-serialization-json").get())
                    }
                }

                sourceSets.getByName("androidMain") {
                    dependencies {
                        implementation(libs.findLibrary("koin-android").get())
                        implementation(libs.findLibrary("kotlinx-coroutines-android").get())
                        implementation(libs.findLibrary("decompose").get())
                    }
                }

                sourceSets.getByName("desktopMain") {
                    dependencies {
                        implementation(libs.findLibrary("kotlinx-coroutines-swing").get())
                    }
                }
            }
        }
    }
}
