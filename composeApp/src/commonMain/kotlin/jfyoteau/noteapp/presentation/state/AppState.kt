package jfyoteau.noteapp.presentation.state

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailState
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListState
import jfyoteau.noteapp.splash.presentation.component.SplashState

interface AppState {
    sealed interface Child {
        data class Splash(val state: SplashState) : Child
        data class NoteList(val state: NoteListState) : Child
        data class NoteDetail(val state: NoteDetailState) : Child
    }

    interface Factory {
        operator fun invoke(componentContext: ComponentContext): AppState
    }

    val childStack: Value<ChildStack<*, Child>>
}
