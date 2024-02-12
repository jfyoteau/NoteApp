package jfyoteau.noteapp.note.data.database

import app.cash.sqldelight.db.SqlDriver

internal const val databaseName = "note.db"

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
