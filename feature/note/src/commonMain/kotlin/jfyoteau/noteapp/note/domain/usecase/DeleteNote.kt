package jfyoteau.noteapp.note.domain.usecase

import jfyoteau.noteapp.note.domain.model.Note

interface DeleteNote {
    suspend operator fun invoke(note: Note)
}
