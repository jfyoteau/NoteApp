package jfyoteau.noteapp.note.presentation.notelist.state

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenState
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder
import jfyoteau.noteapp.note.domain.model.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class DefaultNoteListState(
    componentContext: ComponentContext,
    private val navigation: NoteListNavigation,
    private val useCase: NoteListUseCase,
) : NoteListState,
    DefaultScreenState<NoteListUiState, NoteListUiEvent>(
        componentContext = componentContext,
        initialUiState = NoteListUiState(),
    ) {
    private var getNodesJob: Job? = null
    private var recentlyDeletedNote: Note? = null

    init {
        getNodes(noteOrder = NoteOrder.Date(OrderType.Descending))
    }

    override fun getNodes(noteOrder: NoteOrder) {
        getNodesJob?.cancel()
        getNodesJob = doCancelableAction {
            useCase.getNotesUseCase(noteOrder = noteOrder)
                .onEach { notes ->
                    setUiState {
                        copy(
                            notes = notes,
                            noteOrder = noteOrder,
                        )
                    }
                }
                .collect()
        }
    }

    override fun addNote() = doAction {
        navigation.onAddNote()
    }

    override fun editNote(note: Note) {
        val noteId = note.id ?: return
        navigation.onEditNote(noteId)
    }

    override fun deleteNote(note: Note) = doAction {
        useCase.deleteNoteUseCase(note = note)
        recentlyDeletedNote = note
        setUiEvent {
            NoteListUiEvent.NoteDeleted
        }
    }

    override fun restoreNote() = doAction {
        useCase.addNoteUseCase(note = recentlyDeletedNote ?: return@doAction)
        recentlyDeletedNote = null
    }

    override fun toggleOrderSection() = doAction {
        setUiState {
            copy(
                isOrderSectionVisible = !isOrderSectionVisible
            )
        }
    }
}
