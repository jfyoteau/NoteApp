package jfyoteau.noteapp.convention.plugin

import com.android.build.gradle.LibraryExtension
import jfyoteau.noteapp.convention.configuration.configureKotlinAndroid
import jfyoteau.noteapp.convention.configuration.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }

            // Configure Kotlin Multiplatform
            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatform(this)
            }

            // Configure Android Library
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }
    }
}
