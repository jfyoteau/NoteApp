package jfyoteau.noteapp.splash.presentation.component

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenState
import kotlinx.coroutines.delay

class DefaultSplashState(
    componentContext: ComponentContext,
    private val navigation: SplashNavigation,
) : DefaultScreenState<Unit, SplashUiEvent>(componentContext), SplashState {
    init {
        doAction {
            delay(3000)
            setUiEvent {
                SplashUiEvent.Completed
            }
        }
    }

    override fun setInitialUiState(): Unit = Unit

    override fun gotoNextScreen() = doAction {
        navigation.onCompleted()
    }
}
