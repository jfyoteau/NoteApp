plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.sqldelight.driver.android)
        }
        commonMain.dependencies {
            implementation(projects.core.databaseSqldelight)
            implementation(projects.core.ui)
            implementation(compose.materialIconsExtended)
            implementation(libs.kotlinx.datetime)
            implementation(libs.sqldelight.coroutines)
        }
        desktopMain.dependencies {
            implementation(libs.sqldelight.driver.sqlite)
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
