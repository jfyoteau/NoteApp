package jfyoteau.noteapp.note.presentation.notedetail.state

sealed interface NoteDetailUiEvent {
    data class ShowSnackbar(val message: String) : NoteDetailUiEvent
    data object SaveNote : NoteDetailUiEvent
}
