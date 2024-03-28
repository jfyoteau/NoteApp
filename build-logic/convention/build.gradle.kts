import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "jfyoteau.noteapp.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.jetbrains.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatformApplicationConventionPlugin") {
            id = "convention.kmp.application"
            implementationClass = "jfyoteau.noteapp.convention.plugin.KotlinMultiplatformApplicationConventionPlugin"
        }
        register("kotlinMultiplatformLibraryConventionPlugin") {
            id = "convention.kmp.library"
            implementationClass = "jfyoteau.noteapp.convention.plugin.KotlinMultiplatformLibraryConventionPlugin"
        }
        register("JetbrainsComposeConventionPlugin") {
            id = "convention.jetbrains.compose"
            implementationClass = "jfyoteau.noteapp.convention.plugin.JetbrainsComposeConventionPlugins"
        }
        register("FeatureLibraryConventionPlugin") {
            id = "convention.feature"
            implementationClass = "jfyoteau.noteapp.convention.plugin.FeatureLibraryConventionPlugin"
        }
    }
}
