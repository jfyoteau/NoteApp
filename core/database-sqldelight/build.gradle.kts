plugins {
    alias(libs.plugins.convention.kotlinMultiplatform.library)
    alias(libs.plugins.sqldelight)
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
