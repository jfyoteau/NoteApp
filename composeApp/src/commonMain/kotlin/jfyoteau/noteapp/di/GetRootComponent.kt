package jfyoteau.noteapp.di

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.presentation.component.RootComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GetRootComponent : KoinComponent {
    operator fun invoke(componentContext: ComponentContext): RootComponent {
        val factory: RootComponent.Factory by inject()
        return factory(componentContext)
    }
}
