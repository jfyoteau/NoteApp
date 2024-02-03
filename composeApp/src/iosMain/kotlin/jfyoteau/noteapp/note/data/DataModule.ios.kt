package jfyoteau.noteapp.note.data

import jfyoteau.noteapp.note.data.database.DatabaseDriverFactory
import org.koin.core.module.Module

internal actual fun Module.databaseDriver() {
    single {
        DatabaseDriverFactory().createDriver()
    }
}
