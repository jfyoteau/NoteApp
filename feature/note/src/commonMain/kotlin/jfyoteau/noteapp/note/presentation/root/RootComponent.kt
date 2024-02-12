package jfyoteau.noteapp.note.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.NoteDetailComponent
import jfyoteau.noteapp.note.presentation.notelist.NoteListComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class NoteList(val component: NoteListComponent) : Child
        data class NoteDetail(val component: NoteDetailComponent) : Child
    }

}
