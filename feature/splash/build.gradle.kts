plugins {
    alias(libs.plugins.convention.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.resources)
        }
    }
}

android {
    namespace = "jfyoteau.noteapp.splash"
}
