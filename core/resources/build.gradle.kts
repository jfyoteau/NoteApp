plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.jetbrainsCompose)
}

android {
    namespace = "jfyoteau.noteapp.core.resources"
}

compose.resources {
    publicResClass = true
}
