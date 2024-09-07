package jfyoteau.noteapp.presentation.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import jfyoteau.noteapp.note.presentation.notedetail.screen.NoteDetailScreen
import jfyoteau.noteapp.note.presentation.notelist.screen.NoteListScreen
import jfyoteau.noteapp.presentation.state.AppState
import jfyoteau.noteapp.splash.presentation.screen.SplashScreen

@Composable
fun App(state: AppState) {
    MaterialTheme {
        val childStack by state.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation { child ->
                when (val instance = child.instance) {
                    is AppState.Child.NoteList -> fade()

                    is AppState.Child.NoteDetail ->
                        slide(
                            orientation = if (instance.isNew) {
                                Orientation.Vertical
                            } else {
                                Orientation.Horizontal
                            }
                        )

                    else -> slide()
                }
            },
        ) { child ->
            when (val instance = child.instance) {
                is AppState.Child.Splash -> SplashScreen(state = instance.state)
                is AppState.Child.NoteDetail -> NoteDetailScreen(state = instance.state)
                is AppState.Child.NoteList -> NoteListScreen(state = instance.state)
            }
        }
    }
}
