package jfyoteau.noteapp.note.domain.usecase.internal

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import jfyoteau.noteapp.note.domain.usecase.DeleteNoteUseCase

class DefaultDeleteNoteUseCase(
    private val repository: NoteRepository,
) : DeleteNoteUseCase {
    override suspend fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
