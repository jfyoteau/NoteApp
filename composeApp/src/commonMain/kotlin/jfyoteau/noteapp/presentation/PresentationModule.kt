package jfyoteau.noteapp.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.presentation.component.DefaultRootComponent
import jfyoteau.noteapp.presentation.component.RootComponent
import org.koin.dsl.module

val presentationModule = module {
    single<RootComponent.Factory> {
        object : RootComponent.Factory {
            override operator fun invoke(componentContext: ComponentContext): RootComponent {
                return DefaultRootComponent(
                    componentContext = componentContext,
                    splashFactory = get(),
                    noteListFactory = get(),
                    noteDetailFactory = get(),
                )
            }
        }
    }
}
