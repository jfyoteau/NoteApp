package jfyoteau.noteapp.splash.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.splash.presentation.component.DefaultSplashComponent
import jfyoteau.noteapp.splash.presentation.component.SplashComponent
import jfyoteau.noteapp.splash.presentation.component.SplashNavigation
import org.koin.dsl.module

val presentationModule = module {
    single<SplashComponent.Factory> {
        object : SplashComponent.Factory {
            override fun invoke(
                componentContext: ComponentContext,
                onCompleted: () -> Unit
            ): SplashComponent {
                return DefaultSplashComponent(
                    componentContext = componentContext,
                    navigation = SplashNavigation(
                        onCompleted = onCompleted,
                    ),
                )
            }
        }
    }
}
