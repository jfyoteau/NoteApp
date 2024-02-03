package jfyoteau.noteapp.note.domain.usecase.internal

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import jfyoteau.noteapp.note.domain.usecase.DeleteNote

class DefaultDeleteNote(
    private val repository: NoteRepository,
) : DeleteNote {
    override suspend fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
