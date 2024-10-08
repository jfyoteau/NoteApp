import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.convention.kmp.application)
    alias(libs.plugins.convention.jetbrainsCompose)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            binaryOption("bundleId", "jfyoteau.noteapp")
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.decompose)
        }
        commonMain.dependencies {
            implementation(projects.core.presentation)
            implementation(projects.feature.splash)
            implementation(projects.feature.note)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.slf4j.api)
            implementation(libs.slf4j.reload4j)
        }
    }
}

android {
    namespace = "jfyoteau.noteapp"

    val keystorePropertiesFile = rootProject.file("android_dev_keystore.properties")
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))

    signingConfigs {
        create("dev") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    defaultConfig {
        applicationId = "jfyoteau.noteapp"
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("dev")
        }

        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

compose.desktop {
    application {
        mainClass = "jfyoteau.noteapp.MainKt"

        buildTypes.release {
            proguard {
                configurationFiles.from("compose-desktop.pro")
            }
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jfyoteau.noteapp"
            packageVersion = "1.0.0"
            includeAllModules = true
        }
    }
}
