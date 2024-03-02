package jfyoteau.noteapp.splash.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jfyoteau.noteapp.splash.presentation.state.SplashState
import jfyoteau.noteapp.splash.presentation.state.SplashUiEvent
import kotlinx.coroutines.flow.collectLatest
import noteapp.core.resources.generated.resources.Res
import noteapp.core.resources.generated.resources.loading
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(
    state: SplashState,
) {
    LaunchedEffect(key1 = Unit) {
        state.uiEvent.collectLatest { event ->
            when (event) {
                is SplashUiEvent.Completed -> {
                    state.gotoNextScreen()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(Res.string.loading))
    }
}
