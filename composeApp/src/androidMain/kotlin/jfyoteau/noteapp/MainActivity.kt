package jfyoteau.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import jfyoteau.noteapp.di.GetRootComponent
import jfyoteau.noteapp.presentation.screen.App

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Always create the root component outside Compose on the main thread.
        val root = retainedComponent {
            GetRootComponent(it)
        }

        setContent {
            App(root = root)
        }
    }
}
