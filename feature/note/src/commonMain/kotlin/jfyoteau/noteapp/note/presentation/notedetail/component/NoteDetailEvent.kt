package jfyoteau.noteapp.note.presentation.notedetail.component

import androidx.compose.ui.focus.FocusState

sealed interface NoteDetailEvent {
    data object Back : NoteDetailEvent
    data class EnteredTitle(val value: String) : NoteDetailEvent
    data class ChangeTitleFocus(val focusState: FocusState) : NoteDetailEvent
    data class EnteredContent(val value: String) : NoteDetailEvent
    data class ChangeContentFocus(val focusState: FocusState) : NoteDetailEvent
    data class ChangeColor(val color: Long) : NoteDetailEvent
    data object SaveNote : NoteDetailEvent
}
