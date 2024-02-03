package jfyoteau.noteapp.note.presentation.notelist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.core.decompose.coroutineScope
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder
import jfyoteau.noteapp.note.domain.model.OrderType
import jfyoteau.noteapp.note.domain.usecase.AddNote
import jfyoteau.noteapp.note.domain.usecase.DeleteNote
import jfyoteau.noteapp.note.domain.usecase.GetNotes
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultNoteListComponent(
    componentContext: ComponentContext,
    private val onAddNote: () -> Unit,
    private val onEditNote: (noteId: Long) -> Unit
) : NoteListComponent, ComponentContext by componentContext, KoinComponent {
    private val _state = MutableValue(NoteListState())
    override val state: Value<NoteListState> = _state

    private val coroutineScope = coroutineScope()
    private val getNotes: GetNotes by inject()
    private val addNote: AddNote by inject()
    private val deleteNote: DeleteNote by inject()

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
        getNodesJob = coroutineScope.launch {
            getNotes(noteOrder = event.noteOrder)
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
        coroutineScope.launch {
            deleteNote(note = event.note)
            recentlyDeletedNote = event.note
        }
    }

    private fun doActionRestoreNote() {
        coroutineScope.launch {
            addNote(note = recentlyDeletedNote ?: return@launch)
            recentlyDeletedNote = null
        }
    }

    private fun doActionToggleOrderSection() {
        _state.value = state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
    }

    private fun doActionAddNote() {
        onAddNote()
    }

    private fun doActionEditNote(event: NoteListEvent.EditNote) {
        val noteId = event.note.id ?: return
        onEditNote(noteId)
    }
}
