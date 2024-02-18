plugins {
    alias(libs.plugins.convention.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.materialIconsExtended)
        }
    }
}

android {
    namespace = "jfyoteau.noteapp.splash"
}
