package jfyoteau.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import jfyoteau.noteapp.di.GetAppState
import jfyoteau.noteapp.presentation.screen.App

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Always create the root component outside Compose on the main thread.
        val appState = retainedComponent {
            GetAppState(it)
        }

        setContent {
            App(state = appState)
        }
    }
}
