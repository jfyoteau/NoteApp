package jfyoteau.noteapp.note.presentation.notedetail

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow

interface NoteDetailComponent {
    val state: Value<NoteDetailState>
    val uiEvent: Flow<UiEvent>

    fun onEvent(event: NoteDetailEvent)

    sealed interface UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent
        data object SaveNote : UiEvent
    }
}
