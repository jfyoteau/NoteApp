package jfyoteau.noteapp.note.presentation.notedetail.state

import jfyoteau.noteapp.note.domain.model.Note

data class NoteDetailUiState(
    val isNew: Boolean = true,
    val noteTitle: NoteTextFieldState = NoteTextFieldState(),
    val noteContent: NoteTextFieldState = NoteTextFieldState(),
    val noteColor: Long = Note.generateRandomColor(),
)

data class NoteTextFieldState(
    val text: String = "",
    val isHintVisible: Boolean = true,
)
