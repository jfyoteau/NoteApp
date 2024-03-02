package jfyoteau.noteapp.splash.presentation.state

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.ScreenState

interface SplashState : ScreenState<Unit, SplashUiEvent> {
    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onCompleted: () -> Unit,
        ): SplashState
    }

    fun gotoNextScreen()
}
