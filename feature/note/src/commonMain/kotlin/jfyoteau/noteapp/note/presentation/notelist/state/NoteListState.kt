package jfyoteau.noteapp.note.presentation.notelist.state

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.ScreenState
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder

interface NoteListState : ScreenState<NoteListUiState, NoteListUiEvent> {
    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onAddNote: () -> Unit,
            onEditNote: (noteId: Long) -> Unit,
        ): NoteListState
    }

    fun getNodes(noteOrder: NoteOrder)
    fun addNote()
    fun editNote(note: Note)
    fun deleteNote(note: Note)
    fun restoreNote()
    fun toggleOrderSection()
}
