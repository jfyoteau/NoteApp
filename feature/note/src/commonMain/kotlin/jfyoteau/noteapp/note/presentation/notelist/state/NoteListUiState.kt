package jfyoteau.noteapp.note.presentation.notelist.state

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder
import jfyoteau.noteapp.note.domain.model.OrderType

data class NoteListUiState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
)
