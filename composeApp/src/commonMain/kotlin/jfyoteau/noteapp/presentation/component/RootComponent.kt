package jfyoteau.noteapp.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailComponent
import jfyoteau.noteapp.note.presentation.notelist.component.NoteListComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class NoteList(val component: NoteListComponent) : Child
        data class NoteDetail(val component: NoteDetailComponent) : Child
    }

    interface Factory {
        operator fun invoke(componentContext: ComponentContext): RootComponent
    }
}
