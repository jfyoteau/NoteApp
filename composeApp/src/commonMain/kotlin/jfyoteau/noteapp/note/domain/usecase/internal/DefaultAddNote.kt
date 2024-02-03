package jfyoteau.noteapp.note.domain.usecase.internal

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import jfyoteau.noteapp.note.domain.usecase.AddNote
import jfyoteau.noteapp.note.domain.usecase.InvalidNoteException
import kotlin.coroutines.cancellation.CancellationException

class DefaultAddNote(
    private val repository: NoteRepository,
) : AddNote {
    @Throws(InvalidNoteException::class, CancellationException::class)
    override suspend fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}
