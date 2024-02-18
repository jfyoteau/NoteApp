package jfyoteau.noteapp.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.presentation.state.DefaultRootState
import jfyoteau.noteapp.presentation.state.RootState
import org.koin.dsl.module

val presentationModule = module {
    single<RootState.Factory> {
        object : RootState.Factory {
            override operator fun invoke(componentContext: ComponentContext): RootState {
                return DefaultRootState(
                    componentContext = componentContext,
                    splashFactory = get(),
                    noteListFactory = get(),
                    noteDetailFactory = get(),
                )
            }
        }
    }
}
