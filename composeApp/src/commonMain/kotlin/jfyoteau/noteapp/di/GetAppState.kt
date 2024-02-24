package jfyoteau.noteapp.di

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.presentation.state.AppState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GetAppState : KoinComponent {
    operator fun invoke(componentContext: ComponentContext): AppState {
        val factory: AppState.Factory by inject()
        return factory(componentContext)
    }
}
