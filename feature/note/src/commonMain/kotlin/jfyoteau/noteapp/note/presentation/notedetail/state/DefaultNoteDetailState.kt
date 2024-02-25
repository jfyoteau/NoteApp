package jfyoteau.noteapp.note.presentation.notedetail.state

import androidx.compose.ui.focus.FocusState
import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenState
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.usecase.InvalidNoteException
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DefaultNoteDetailState(
    componentContext: ComponentContext,
    private val noteId: Long?,
    private val navigation: NoteDetailNavigation,
    private val useCase: NoteDetailUseCase,
) : NoteDetailState, DefaultScreenState<NoteDetailUiState, NoteDetailUiEvent>(componentContext) {
    init {
        if (noteId != null) {
            getNote(noteId = noteId)
        }
    }

    override fun setInitialUiState(): NoteDetailUiState = NoteDetailUiState()


    override fun back() = doAction {
        navigation.onBack()
    }

    override fun enterTitle(value: String) = doAction {
        setUiState {
            copy(
                noteTitle = noteTitle.copy(
                    text = value
                ),
            )
        }
    }

    override fun changeTitleFocus(focusState: FocusState) = doAction {
        setUiState {
            copy(
                noteTitle = noteTitle.copy(
                    isHintVisible = !focusState.isFocused && noteTitle.text.isBlank(),
                ),
            )
        }
    }

    override fun enterContent(value: String) = doAction {
        setUiState {
            copy(
                noteContent = noteContent.copy(
                    text = value,
                ),
            )
        }
    }

    override fun changeContentFocus(focusState: FocusState) = doAction {
        setUiState {
            copy(
                noteContent = noteContent.copy(
                    isHintVisible = !focusState.isFocused && noteContent.text.isBlank(),
                ),
            )
        }
    }

    override fun changeColor(color: Long) = doAction {
        setUiState {
            copy(
                noteColor = color,
            )
        }
    }

    override fun saveNote() = doAction {
        val currentMoment = Clock.System.now()
        val now = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        try {
            useCase.addNote(
                note = Note(
                    id = noteId,
                    title = uiState.value.noteTitle.text,
                    content = uiState.value.noteContent.text,
                    color = uiState.value.noteColor,
                    createdAt = now,
                )
            )
            setUiEvent {
                NoteDetailUiEvent.SaveNote
            }
        } catch (e: InvalidNoteException) {
            setUiEvent {
                NoteDetailUiEvent.ShowSnackbar(
                    message = e.message ?: "Couldn't save note"
                )
            }
        }
    }

    private fun getNote(noteId: Long) = doAction {
        useCase.getNote(id = noteId)?.also { note ->
            setUiState {
                copy(
                    isNew = false,
                    noteTitle = noteTitle.copy(
                        text = note.title,
                    ),
                    noteContent = noteContent.copy(
                        text = note.content,
                    ),
                    noteColor = note.color,
                )
            }
        }
    }
}
