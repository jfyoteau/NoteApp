package jfyoteau.noteapp.note.presentation.notelist.component

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.ScreenComponent
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.model.NoteOrder

interface NoteListComponent : ScreenComponent<NoteListUiState, Nothing> {
    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onAddNote: () -> Unit,
            onEditNote: (noteId: Long) -> Unit,
        ): NoteListComponent
    }

    fun getNodes(noteOrder: NoteOrder)
    fun addNote()
    fun editNote(note: Note)
    fun deleteNote(note: Note)
    fun restoreNote()
    fun toggleOrderSection()
}
