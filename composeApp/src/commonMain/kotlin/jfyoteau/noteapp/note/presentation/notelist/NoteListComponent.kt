package jfyoteau.noteapp.note.presentation.notelist

import com.arkivanov.decompose.value.Value

interface NoteListComponent {
    val state: Value<NoteListState>

    fun onEvent(event: NoteListEvent)
}
