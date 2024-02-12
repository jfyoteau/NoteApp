package jfyoteau.noteapp.note.domain

import jfyoteau.noteapp.note.domain.usecase.AddNote
import jfyoteau.noteapp.note.domain.usecase.DeleteNote
import jfyoteau.noteapp.note.domain.usecase.GetNote
import jfyoteau.noteapp.note.domain.usecase.GetNotes
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultAddNote
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultDeleteNote
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultGetNote
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultGetNotes
import org.koin.dsl.module

internal val domainModule = module {
    single<AddNote> {
        DefaultAddNote(repository = get())
    }

    single<DeleteNote> {
        DefaultDeleteNote(repository = get())
    }

    single<GetNotes> {
        DefaultGetNotes(repository = get())
    }

    single<GetNote> {
        DefaultGetNote(repository = get())
    }
}
