package jfyoteau.noteapp.note.presentation.notedetail.component

import com.arkivanov.decompose.ComponentContext
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

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            noteId: Long?,
            onBack: () -> Unit,
        ): NoteDetailComponent
    }
}
