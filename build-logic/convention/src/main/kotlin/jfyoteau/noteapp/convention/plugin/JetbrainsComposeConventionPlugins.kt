package jfyoteau.noteapp.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import jfyoteau.noteapp.convention.configuration.configureAndroidCompose
import jfyoteau.noteapp.convention.configuration.configureJetbrainsCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class JetbrainsComposeConventionPlugins: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            if (!pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
                logger.error("Must apply 'org.jetbrains.kotlin.multiplatform' plugin before using 'convention.jetbrains.compose' plugin.")
                return@with
            }

            val kotlinMultiplatformExtension = extensions.getByType<KotlinMultiplatformExtension>()
            configureJetbrainsCompose(kotlinMultiplatformExtension)

            if (pluginManager.hasPlugin("com.android.application")) {
                val androidExtension = extensions.getByType<ApplicationExtension>()
                configureAndroidCompose(androidExtension)
            } else if (pluginManager.hasPlugin("com.android.library")) {
                val androidExtension = extensions.getByType<LibraryExtension>()
                configureAndroidCompose(androidExtension)
            } else {
                logger.error("Must apply 'com.android.application' plugin or 'com.android.application' plugin before using 'convention.jetbrains.compose' plugin.")
            }
        }
    }
}
