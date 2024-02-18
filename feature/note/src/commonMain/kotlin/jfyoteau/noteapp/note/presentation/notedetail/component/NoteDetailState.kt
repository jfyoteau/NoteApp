package jfyoteau.noteapp.note.presentation.notedetail.component

import jfyoteau.noteapp.note.domain.model.Note

data class NoteDetailState(
    val noteTitle: NoteTextFieldState = NoteTextFieldState(),
    val noteContent: NoteTextFieldState = NoteTextFieldState(),
    val noteColor: Long = Note.generateRandomColor(),
)

data class NoteTextFieldState(
    val text: String = "",
    val isHintVisible: Boolean = true,
)
