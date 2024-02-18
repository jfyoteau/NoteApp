package jfyoteau.noteapp.note.presentation.notedetail.component

import androidx.compose.ui.focus.FocusState
import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.ScreenComponent

interface NoteDetailComponent : ScreenComponent<NoteDetailUiState, NoteDetailUiEvent> {
    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            noteId: Long?,
            onBack: () -> Unit,
        ): NoteDetailComponent
    }

    fun back()
    fun enterTitle(value: String)
    fun changeTitleFocus(focusState: FocusState)
    fun enterContent(value: String)
    fun changeContentFocus(focusState: FocusState)
    fun changeColor(color: Long)
    fun saveNote()
}
