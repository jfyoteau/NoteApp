import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.lifecycle.ApplicationLifecycle
import jfyoteau.noteapp.presentation.screen.App
import jfyoteau.noteapp.di.GetRootState

@OptIn(ExperimentalDecomposeApi::class)
@Suppress("unused")
fun MainViewController() = ComposeUIViewController {
    val rootState = remember {
        GetRootState(DefaultComponentContext(ApplicationLifecycle()))
    }
    App(state = rootState)
}
