package jfyoteau.noteapp.note.presentation.notelist.component

import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder

sealed interface NoteListEvent {
    data object AddNote : NoteListEvent
    data class DeleteNote(val note: Note) : NoteListEvent
    data class  EditNote(val note: Note) : NoteListEvent
    data class Order(val noteOrder: NoteOrder) : NoteListEvent
    data object RestoreNote : NoteListEvent
    data object ToggleOrderSection : NoteListEvent
}
