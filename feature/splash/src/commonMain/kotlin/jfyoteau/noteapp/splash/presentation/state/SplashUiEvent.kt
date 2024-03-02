package jfyoteau.noteapp.splash.presentation.state

sealed interface SplashUiEvent {
    data object Completed : SplashUiEvent
}
