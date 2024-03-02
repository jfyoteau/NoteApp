package jfyoteau.noteapp.splash.domain.usecase.internal

import jfyoteau.noteapp.splash.domain.usecase.LoadingUseCase
import kotlinx.coroutines.delay

class DefaultLoadingUseCase : LoadingUseCase {
    override suspend operator fun invoke() {
        delay(3000)
    }
}
