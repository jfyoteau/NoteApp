package jfyoteau.noteapp.splash.presentation.component

sealed interface SplashUiEvent {
    data object Completed : SplashUiEvent
}
