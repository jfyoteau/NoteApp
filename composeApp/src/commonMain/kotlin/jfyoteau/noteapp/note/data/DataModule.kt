package jfyoteau.noteapp.note.data

import jfyoteau.noteapp.core.database.adapter.localDateTimeAdapter
import jfyoteau.noteapp.note.data.database.Note
import jfyoteau.noteapp.note.data.database.NoteDatabase
import jfyoteau.noteapp.note.data.database.NoteQueries
import jfyoteau.noteapp.note.data.repository.NoteRepositoryDefault
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun Module.databaseDriver()

internal val dataModule = module {
    databaseDriver()
    single {
        NoteDatabase(
            driver = get(),
            noteAdapter = Note.Adapter(
                created_atAdapter = localDateTimeAdapter,
            )
        )
    }

    single<NoteQueries> {
        get<NoteDatabase>().noteQueries
    }

    single<NoteRepository> {
        NoteRepositoryDefault(noteQueries = get())
    }
}
