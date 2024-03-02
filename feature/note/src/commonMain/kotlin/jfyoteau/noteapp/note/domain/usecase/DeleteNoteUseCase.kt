package jfyoteau.noteapp.note.domain.usecase

import jfyoteau.noteapp.note.domain.model.Note

interface DeleteNoteUseCase {
    suspend operator fun invoke(note: Note)
}
