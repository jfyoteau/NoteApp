import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import jfyoteau.noteapp.di.initKoin

fun main() = application {
    initKoin()

    Window(onCloseRequest = ::exitApplication, title = "NoteApp") {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}
