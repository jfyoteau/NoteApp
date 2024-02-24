package jfyoteau.noteapp.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.presentation.state.DefaultAppState
import jfyoteau.noteapp.presentation.state.AppState
import org.koin.dsl.module

val presentationModule = module {
    single<AppState.Factory> {
        object : AppState.Factory {
            override operator fun invoke(componentContext: ComponentContext): AppState {
                return DefaultAppState(
                    componentContext = componentContext,
                    splashFactory = get(),
                    noteListFactory = get(),
                    noteDetailFactory = get(),
                )
            }
        }
    }
}
