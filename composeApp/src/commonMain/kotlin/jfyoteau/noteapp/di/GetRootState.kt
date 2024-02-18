package jfyoteau.noteapp.di

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.presentation.state.RootState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GetRootState : KoinComponent {
    operator fun invoke(componentContext: ComponentContext): RootState {
        val factory: RootState.Factory by inject()
        return factory(componentContext)
    }
}
