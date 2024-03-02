package jfyoteau.noteapp.note.domain.usecase

import jfyoteau.noteapp.note.domain.model.Note
import kotlin.coroutines.cancellation.CancellationException

interface AddNoteUseCase {
    @Throws(InvalidNoteException::class, CancellationException::class)
    suspend operator fun invoke(note: Note)
}
