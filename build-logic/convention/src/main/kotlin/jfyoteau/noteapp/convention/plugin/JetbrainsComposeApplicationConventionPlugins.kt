package jfyoteau.noteapp.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import jfyoteau.noteapp.convention.configuration.configureAndroidCompose
import jfyoteau.noteapp.convention.configuration.configureJetbrainsCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class JetbrainsComposeApplicationConventionPlugins: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.application")
                apply("org.jetbrains.compose")
            }

            val kotlinMultiplatformExtension = extensions.getByType<KotlinMultiplatformExtension>()
            configureJetbrainsCompose(kotlinMultiplatformExtension)

            val libraryExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(libraryExtension)
        }
    }
}
