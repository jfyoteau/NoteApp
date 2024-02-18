package jfyoteau.appnote.core.presentation

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow

interface ScreenComponent<UiState : Any, UiEvent> {
    val uiState: Value<UiState>
    val uiEvent: Flow<UiEvent>
}
