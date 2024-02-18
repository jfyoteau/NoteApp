package jfyoteau.noteapp.presentation.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import jfyoteau.noteapp.note.presentation.notedetail.screen.NoteDetailScreen
import jfyoteau.noteapp.note.presentation.notelist.screen.NoteListScreen
import jfyoteau.noteapp.presentation.component.RootComponent

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        childStack.active.instance
        Children(
            stack = childStack,
            animation = stackAnimation(slide()),
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.NoteDetail -> NoteDetailScreen(component = instance.component)
                is RootComponent.Child.NoteList -> NoteListScreen(component = instance.component)
            }
        }
    }
}
