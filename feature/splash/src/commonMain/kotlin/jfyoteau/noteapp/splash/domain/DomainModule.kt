package jfyoteau.noteapp.splash.domain

import jfyoteau.noteapp.splash.domain.usecase.LoadingUseCase
import jfyoteau.noteapp.splash.domain.usecase.internal.DefaultLoadingUseCase
import org.koin.dsl.module

val domainModule = module {
    single<LoadingUseCase> {
        DefaultLoadingUseCase()
    }
}
