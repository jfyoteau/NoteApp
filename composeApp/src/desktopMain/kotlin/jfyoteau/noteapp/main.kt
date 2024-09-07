package jfyoteau.noteapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import jfyoteau.noteapp.di.GetAppState
import jfyoteau.noteapp.di.initKoin
import jfyoteau.noteapp.presentation.screen.App

fun main() {
    // Initialize Koin
    initKoin()

    val lifecycle = LifecycleRegistry()

    // Always create the root component outside Compose on the UI thread.
    val appState =
        runOnUiThread {
            GetAppState(DefaultComponentContext(lifecycle = lifecycle))
        }

    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "NoteApp"
        ) {
            App(state = appState)
        }
    }
}
