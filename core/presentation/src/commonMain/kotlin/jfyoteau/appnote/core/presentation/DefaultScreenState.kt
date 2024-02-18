package jfyoteau.appnote.core.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class DefaultScreenState<UiState : Any, UiEvent>(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, ScreenState<UiState, UiEvent> {
    private val componentScope = coroutineScope()

    protected abstract fun setInitialUiState(): UiState

    private val initialUiState: UiState by lazy {
        setInitialUiState()
    }
    private val _uiState = MutableValue(initialUiState)
    override val uiState: Value<UiState> = _uiState

    private val _uiEvent: Channel<UiEvent> = Channel()
    override val uiEvent = _uiEvent.receiveAsFlow()

    protected fun setUiState(reducer: UiState.() -> UiState) {
        _uiState.value = uiState.value.reducer()
    }

    protected fun setUiEvent(builder: () -> UiEvent) {
        val uiEventValue = builder()
        componentScope.launch {
            _uiEvent.send(uiEventValue)
        }
    }

    protected fun doAction(action: suspend () -> Unit) {
        componentScope.launch {
            action()
        }
    }

    protected fun doCancelableAction(action: suspend () -> Unit): Job {
        return componentScope.launch {
            action()
        }
    }
}

private fun LifecycleOwner.coroutineScope(
    context: CoroutineContext = Dispatchers.Main.immediate,
): CoroutineScope {
    val scope = CoroutineScope(context + SupervisorJob())
    lifecycle.doOnDestroy(scope::cancel)

    return scope
}
