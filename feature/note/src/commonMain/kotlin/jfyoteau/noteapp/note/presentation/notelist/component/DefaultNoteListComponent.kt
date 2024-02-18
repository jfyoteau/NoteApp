package jfyoteau.noteapp.note.presentation.notelist.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import jfyoteau.appnote.core.presentation.ScreenComponent
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder
import jfyoteau.noteapp.note.domain.model.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DefaultNoteListComponent(
    componentContext: ComponentContext,
    private val navigation: NoteListNavigation,
    private val useCase: NoteListUseCase,
) : NoteListComponent, ScreenComponent(componentContext) {
    private val _state = MutableValue(NoteListState())
    override val state: Value<NoteListState> = _state

    private var getNodesJob: Job? = null
    private var recentlyDeletedNote: Note? = null

    init {
        onEvent(event = NoteListEvent.Order(noteOrder = NoteOrder.Date(OrderType.Descending)))
    }

    override fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.DeleteNote -> doActionDeleteNotes(event)
            is NoteListEvent.EditNote -> doActionEditNote(event)
            is NoteListEvent.Order -> doActionGetNotes(event)
            NoteListEvent.RestoreNote -> doActionRestoreNote()
            NoteListEvent.ToggleOrderSection -> doActionToggleOrderSection()
            NoteListEvent.AddNote -> doActionAddNote()
        }
    }

    private fun doActionGetNotes(event: NoteListEvent.Order) {
        getNodesJob?.cancel()
        getNodesJob = componentScope.launch {
            useCase.getNotes(noteOrder = event.noteOrder)
                .onEach { notes ->
                    _state.value = state.value.copy(
                        notes = notes,
                        noteOrder = event.noteOrder,
                    )
                }
                .collect()
        }
    }

    private fun doActionDeleteNotes(event: NoteListEvent.DeleteNote) {
        componentScope.launch {
            useCase.deleteNote(note = event.note)
            recentlyDeletedNote = event.note
        }
    }

    private fun doActionRestoreNote() {
        componentScope.launch {
            useCase.addNote(note = recentlyDeletedNote ?: return@launch)
            recentlyDeletedNote = null
        }
    }

    private fun doActionToggleOrderSection() {
        _state.value = state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
    }

    private fun doActionAddNote() {
        navigation.onAddNote()
    }

    private fun doActionEditNote(event: NoteListEvent.EditNote) {
        val noteId = event.note.id ?: return
        navigation.onEditNote(noteId)
    }
}
