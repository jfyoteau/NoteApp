package jfyoteau.noteapp.splash.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.splash.presentation.state.DefaultSplashState
import jfyoteau.noteapp.splash.presentation.state.SplashState
import jfyoteau.noteapp.splash.presentation.state.SplashNavigation
import org.koin.dsl.module

val presentationModule = module {
    single<SplashState.Factory> {
        object : SplashState.Factory {
            override fun invoke(
                componentContext: ComponentContext,
                onCompleted: () -> Unit
            ): SplashState {
                return DefaultSplashState(
                    componentContext = componentContext,
                    navigation = SplashNavigation(
                        onCompleted = onCompleted,
                    ),
                    loadingUseCase = get(),
                )
            }
        }
    }
}
