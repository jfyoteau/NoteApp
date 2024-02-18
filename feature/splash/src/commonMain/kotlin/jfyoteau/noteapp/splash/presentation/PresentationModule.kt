package jfyoteau.noteapp.splash.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.splash.presentation.component.DefaultSplashState
import jfyoteau.noteapp.splash.presentation.component.SplashState
import jfyoteau.noteapp.splash.presentation.component.SplashNavigation
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
                )
            }
        }
    }
}
