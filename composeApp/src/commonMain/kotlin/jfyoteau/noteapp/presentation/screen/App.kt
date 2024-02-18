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
import jfyoteau.noteapp.presentation.state.RootState
import jfyoteau.noteapp.splash.presentation.screen.SplashScreen

@Composable
fun App(state: RootState) {
    MaterialTheme {
        val childStack by state.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide()),
        ) { child ->
            when (val instance = child.instance) {
                is RootState.Child.Splash -> SplashScreen(state = instance.state)
                is RootState.Child.NoteDetail -> NoteDetailScreen(state = instance.state)
                is RootState.Child.NoteList -> NoteListScreen(state = instance.state)
            }
        }
    }
}
