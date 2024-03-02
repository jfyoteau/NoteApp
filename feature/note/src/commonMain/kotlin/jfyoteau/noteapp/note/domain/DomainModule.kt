package jfyoteau.noteapp.note.domain

import jfyoteau.noteapp.note.domain.usecase.AddNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.DeleteNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.GetNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.GetNotesUseCase
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultAddNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultDeleteNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultGetNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.internal.DefaultGetNotesUseCase
import org.koin.dsl.module

internal val domainModule = module {
    single<AddNoteUseCase> {
        DefaultAddNoteUseCase(repository = get())
    }

    single<DeleteNoteUseCase> {
        DefaultDeleteNoteUseCase(repository = get())
    }

    single<GetNotesUseCase> {
        DefaultGetNotesUseCase(repository = get())
    }

    single<GetNoteUseCase> {
        DefaultGetNoteUseCase(repository = get())
    }
}
