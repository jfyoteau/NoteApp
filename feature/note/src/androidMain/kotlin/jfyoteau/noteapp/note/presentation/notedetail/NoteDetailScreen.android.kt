package jfyoteau.noteapp.note.presentation.notedetail

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailComponent
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailUiEvent
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailUiState
import jfyoteau.noteapp.note.presentation.notedetail.screen.NoteDetailScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Composable
private fun CommonPreview(state: NoteDetailUiState) {
    MaterialTheme {
        NoteDetailScreen(
            component = object : NoteDetailComponent {
                override val uiState: Value<NoteDetailUiState> = MutableValue(state)
                override val uiEvent: Flow<NoteDetailUiEvent> =
                    MutableSharedFlow<NoteDetailUiEvent>().asSharedFlow()

                override fun back() = Unit

                override fun enterTitle(value: String) = Unit

                override fun changeTitleFocus(focusState: FocusState) = Unit

                override fun enterContent(value: String) = Unit

                override fun changeContentFocus(focusState: FocusState) = Unit

                override fun changeColor(color: Long) = Unit

                override fun saveNote() = Unit
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
        state = NoteDetailUiState(
            noteColor = 0xffffff00,
        )
    )
}
