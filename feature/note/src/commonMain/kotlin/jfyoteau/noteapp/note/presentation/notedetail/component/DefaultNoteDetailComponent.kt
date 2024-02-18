package jfyoteau.noteapp.note.presentation.notedetail.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import jfyoteau.appnote.core.presentation.ScreenComponent
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.usecase.InvalidNoteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DefaultNoteDetailComponent(
    componentContext: ComponentContext,
    private val noteId: Long?,
    private val navigation: NoteDetailNavigation,
    private val useCase: NoteDetailUseCase,
) : NoteDetailComponent, ScreenComponent(componentContext) {
    private val _state = MutableValue(NoteDetailState())
    override val state: Value<NoteDetailState> = _state

    private val _uiEvent = MutableSharedFlow<NoteDetailComponent.UiEvent>()
    override val uiEvent: Flow<NoteDetailComponent.UiEvent> = _uiEvent.asSharedFlow()

    init {
        if (noteId != null) {
            componentScope.launch {
                useCase.getNote(id = noteId)?.also { note ->
                    _state.value = state.value.copy(
                        noteTitle = state.value.noteTitle.copy(
                            text = note.title,
                        ),
                        noteContent = state.value.noteContent.copy(
                            text = note.content,
                        ),
                        noteColor = note.color,
                    )
                }
            }
        }
    }

    override fun onEvent(event: NoteDetailEvent) {
        when (event) {
            is NoteDetailEvent.ChangeColor -> doActionChangeColor(event)
            is NoteDetailEvent.ChangeContentFocus -> doActionChangeContentFocus(event)
            is NoteDetailEvent.ChangeTitleFocus -> doActionChangeTitleFocus(event)
            is NoteDetailEvent.EnteredContent -> doActionEnteredContent(event)
            is NoteDetailEvent.EnteredTitle -> doActionEnteredTitle(event)
            NoteDetailEvent.SaveNote -> doActionSaveNote()
            NoteDetailEvent.Back -> doActionBack()
        }
    }

    private fun doActionBack() {
        navigation.onBack()
    }

    private fun doActionChangeColor(event: NoteDetailEvent.ChangeColor) {
        _state.value = state.value.copy(
            noteColor = event.color
        )
    }

    private fun doActionChangeContentFocus(event: NoteDetailEvent.ChangeContentFocus) {
        _state.value = state.value.copy(
            noteContent = state.value.noteContent.copy(
                isHintVisible = !event.focusState.isFocused && state.value.noteContent.text.isBlank()
            ),
        )
    }

    private fun doActionChangeTitleFocus(event: NoteDetailEvent.ChangeTitleFocus) {
        _state.value = state.value.copy(
            noteTitle = state.value.noteTitle.copy(
                isHintVisible = !event.focusState.isFocused && state.value.noteTitle.text.isBlank()
            ),
        )
    }

    private fun doActionEnteredContent(event: NoteDetailEvent.EnteredContent) {
        _state.value = state.value.copy(
            noteContent = state.value.noteContent.copy(
                text = event.value
            )
        )
    }

    private fun doActionEnteredTitle(event: NoteDetailEvent.EnteredTitle) {
        _state.value = state.value.copy(
            noteTitle = state.value.noteTitle.copy(
                text = event.value
            )
        )
    }

    private fun doActionSaveNote() {
        componentScope.launch {
            val currentMoment = Clock.System.now()
            val now = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
            try {
                useCase.addNote(
                    note = Note(
                        id = noteId,
                        title = state.value.noteTitle.text,
                        content = state.value.noteContent.text,
                        color = state.value.noteColor,
                        createdAt = now,
                    )
                )
                _uiEvent.emit(NoteDetailComponent.UiEvent.SaveNote)
            } catch (e: InvalidNoteException) {
                _uiEvent.emit(
                    NoteDetailComponent.UiEvent.ShowSnackbar(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }
        }
    }
}
