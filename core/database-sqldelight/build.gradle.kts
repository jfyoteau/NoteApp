plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.sqldelight.runtime)
            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "jfyoteau.noteapp.core.database.sqldelight"
}
