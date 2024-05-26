import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import jfyoteau.noteapp.presentation.screen.App
import jfyoteau.noteapp.di.GetAppState

@OptIn(ExperimentalDecomposeApi::class)
@Suppress("unused")
fun MainViewController() = ComposeUIViewController {
    val appState = remember {
        GetAppState(DefaultComponentContext(ApplicationLifecycle()))
    }
    App(state = appState)
}
