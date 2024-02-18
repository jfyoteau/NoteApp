package jfyoteau.noteapp.splash.presentation.component

import com.arkivanov.decompose.ComponentContext
import jfyoteau.appnote.core.presentation.ScreenComponent

interface SplashComponent : ScreenComponent<Unit, SplashUiEvent> {
    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onCompleted: () -> Unit,
        ): SplashComponent
    }

    fun gotoNextScreen()
}
