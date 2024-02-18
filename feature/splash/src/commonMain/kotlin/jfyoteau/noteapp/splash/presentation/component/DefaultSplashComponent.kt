package jfyoteau.noteapp.splash.presentation.component

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenComponent
import kotlinx.coroutines.delay

class DefaultSplashComponent(
    componentContext: ComponentContext,
    private val navigation: SplashNavigation,
) : DefaultScreenComponent<Unit, SplashUiEvent>(componentContext), SplashComponent {
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
