plugins {
    alias(libs.plugins.convention.kotlinMultiplatform.library)
    alias(libs.plugins.convention.jetbrainsCompose.library)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.sqldelight.driver.android)
            implementation(libs.decompose)
        }
        commonMain.dependencies {
            implementation(projects.core.databaseSqldelight)
            implementation(projects.core.decompose)
            implementation(projects.core.ui)
            implementation(compose.materialIconsExtended)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.sqldelight.coroutines)
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.kotlinx.serialization.json)
        }
        desktopMain.dependencies {
            implementation(libs.sqldelight.driver.sqlite)
            implementation(libs.kotlinx.coroutines.swing)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.driver.native)
        }
    }
}

android {
    namespace = "jfyoteau.noteapp.note"
}

sqldelight {
    databases {
        create("NoteDatabase") {
            packageName.set("jfyoteau.noteapp.note.data.database")
        }
    }
}
