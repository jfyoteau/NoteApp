package jfyoteau.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import jfyoteau.noteapp.note.presentation.root.DefaultRootComponent

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Always create the root component outside Compose on the main thread.
        val root = retainedComponent {
            DefaultRootComponent(componentContext = it)
        }

        setContent {
            App(root = root)
        }
    }
}
