package jfyoteau.noteapp.note.presentation.notelist.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

interface NoteListComponent {
    val state: Value<NoteListState>

    fun onEvent(event: NoteListEvent)

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onAddNote: () -> Unit,
            onEditNote: (noteId: Long) -> Unit,
        ): NoteListComponent
    }
}
