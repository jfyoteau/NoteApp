package jfyoteau.noteapp.note.domain.usecase.internal

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder
import jfyoteau.noteapp.note.domain.model.OrderType
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import jfyoteau.noteapp.note.domain.usecase.GetNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultGetNotes(
    private val repository: NoteRepository,
) : GetNotes {
    override suspend operator fun invoke(noteOrder: NoteOrder): Flow<List<Note>> {
        return repository.getAllNotes().map { notes ->
            when (noteOrder.orderType) {
                OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.createdAt }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }

                OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.createdAt }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}
