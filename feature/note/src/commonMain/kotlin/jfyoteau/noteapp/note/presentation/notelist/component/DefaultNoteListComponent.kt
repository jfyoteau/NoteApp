package jfyoteau.noteapp.note.presentation.notelist.component

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenComponent
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder
import jfyoteau.noteapp.note.domain.model.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class DefaultNoteListComponent(
    componentContext: ComponentContext,
    private val navigation: NoteListNavigation,
    private val useCase: NoteListUseCase,
) : NoteListComponent, DefaultScreenComponent<NoteListUiState, Nothing>(componentContext) {
    private var getNodesJob: Job? = null
    private var recentlyDeletedNote: Note? = null

    init {
        getNodes(noteOrder = NoteOrder.Date(OrderType.Descending))
    }

    override fun setInitialUiState(): NoteListUiState = NoteListUiState()

    override fun getNodes(noteOrder: NoteOrder) {
        getNodesJob?.cancel()
        getNodesJob = doCancelableAction {
            useCase.getNotes(noteOrder = noteOrder)
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
        useCase.deleteNote(note = note)
        recentlyDeletedNote = note
    }

    override fun restoreNote() = doAction {
        useCase.addNote(note = recentlyDeletedNote ?: return@doAction)
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
