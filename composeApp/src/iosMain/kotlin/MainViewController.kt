import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.lifecycle.ApplicationLifecycle
import jfyoteau.noteapp.App
import jfyoteau.noteapp.presentation.DefaultRootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Suppress("unused")
fun MainViewController() = ComposeUIViewController {
    val root = remember {
        DefaultRootComponent(DefaultComponentContext(ApplicationLifecycle()))
    }
    App(root = root)
}
