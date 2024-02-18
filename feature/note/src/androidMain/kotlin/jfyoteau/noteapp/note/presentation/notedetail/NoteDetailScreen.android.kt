package jfyoteau.noteapp.note.presentation.notedetail

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailComponent
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailEvent
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailState
import jfyoteau.noteapp.note.presentation.notedetail.screen.NoteDetailScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Composable
private fun CommonPreview(state: NoteDetailState) {
    MaterialTheme {
        NoteDetailScreen(
            component = object: NoteDetailComponent {
                override val state: Value<NoteDetailState> = MutableValue(state)
                override val uiEvent: Flow<NoteDetailComponent.UiEvent> = MutableSharedFlow<NoteDetailComponent.UiEvent>().asSharedFlow()

                override fun onEvent(event: NoteDetailEvent) {
                    // Do nothing.
                }
            },
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun NoteDetailScreenPreview() {
    CommonPreview(
        state = NoteDetailState(
            noteColor = 0xff00ff,
        )
    )
}
