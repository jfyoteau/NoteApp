package jfyoteau.noteapp.note.domain.usecase.internal

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import jfyoteau.noteapp.note.domain.usecase.GetNoteUseCase

class DefaultGetNoteUseCase(
    private val repository: NoteRepository,
) : GetNoteUseCase {
    override suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id)
    }
}
