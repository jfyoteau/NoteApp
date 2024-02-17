plugins {
    alias(libs.plugins.convention.kotlinMultiplatform.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.decompose)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "jfyoteau.noteapp.core.decompose"
}
