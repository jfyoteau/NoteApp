package jfyoteau.noteapp.splash.presentation.state

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.DefaultScreenState
import jfyoteau.noteapp.splash.domain.usecase.LoadingUseCase

class DefaultSplashState(
    componentContext: ComponentContext,
    private val navigation: SplashNavigation,
    private val loadingUseCase: LoadingUseCase,
) : SplashState,
    DefaultScreenState<Unit, SplashUiEvent>(
        componentContext = componentContext,
        initialUiState = Unit,
    ) {
    init {
        doAction {
            loadingUseCase()
            setUiEvent {
                SplashUiEvent.Completed
            }
        }
    }

    override fun gotoNextScreen() = doAction {
        navigation.onCompleted()
    }
}
