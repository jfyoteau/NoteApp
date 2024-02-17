package jfyoteau.noteapp.convention.configuration

import com.android.build.api.dsl.CommonExtension
import jfyoteau.noteapp.convention.AndroidBuildConfig
import jfyoteau.noteapp.convention.extensions.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    val androidManifestFile = file("src/androidMain/AndroidManifest.xml")
    val resFile = file("src/androidMain/res")

    commonExtension.apply {
        compileSdk = AndroidBuildConfig.compileSdkVersion

        if (androidManifestFile.exists()) {
            sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
        if (resFile.exists()) {
            sourceSets["main"].res.srcDirs("src/androidMain/res")
        }

        defaultConfig {
            minSdk = AndroidBuildConfig.minSdkVersion
        }

        compileOptions {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = AndroidBuildConfig.jvmTarget
            targetCompatibility = AndroidBuildConfig.jvmTarget
            isCoreLibraryDesugaringEnabled = true
        }
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
            }
        }
    }

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}
