package jfyoteau.noteapp.splash.presentation.state

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenState
import kotlinx.coroutines.delay

class DefaultSplashState(
    componentContext: ComponentContext,
    private val navigation: SplashNavigation,
) : SplashState,
    DefaultScreenState<Unit, SplashUiEvent>(
        componentContext = componentContext,
        initialUiState = Unit,
    ) {
    init {
        doAction {
            delay(3000)
            setUiEvent {
                SplashUiEvent.Completed
            }
        }
    }

    override fun gotoNextScreen() = doAction {
        navigation.onCompleted()
    }
}
