package jfyoteau.noteapp.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import jfyoteau.noteapp.convention.AndroidBuildConfig
import jfyoteau.noteapp.convention.configuration.configureKotlinAndroid
import jfyoteau.noteapp.convention.configuration.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class KotlinMultiplatformApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.application")
            }

            // Configure Kotlin Multiplatform
            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatform(this)
            }

            // Configure Android Application
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidBuildConfig.targetSdkVersion
            }
        }
    }
}
