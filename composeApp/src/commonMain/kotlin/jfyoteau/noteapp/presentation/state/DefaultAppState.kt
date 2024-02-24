package jfyoteau.noteapp.presentation.state

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailState
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListState
import jfyoteau.noteapp.splash.presentation.component.SplashState
import kotlinx.serialization.Serializable

class DefaultAppState(
    componentContext: ComponentContext,
    private val splashFactory: SplashState.Factory,
    private val noteListFactory: NoteListState.Factory,
    private val noteDetailFactory: NoteDetailState.Factory,
) : AppState, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, AppState.Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Splash,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        configuration: Configuration,
        context: ComponentContext,
    ): AppState.Child {
        return when (configuration) {
            is Configuration.Splash -> AppState.Child.Splash(
                splashComponent(context)
            )

            is Configuration.NoteList -> AppState.Child.NoteList(
                listComponent(context)
            )

            is Configuration.NoteDetail -> AppState.Child.NoteDetail(
                detailComponent(context, configuration.noteId)
            )
        }
    }

    private fun splashComponent(context: ComponentContext): SplashState {
        return splashFactory(
            componentContext = context,
            onCompleted = {
                navigation.replaceAll(Configuration.NoteList)
            }
        )
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun listComponent(context: ComponentContext): NoteListState {
        return noteListFactory(
            componentContext = context,
            onAddNote = {
                navigation.pushNew(Configuration.NoteDetail())
            },
            onEditNote = { noteId ->
                navigation.pushNew(Configuration.NoteDetail(noteId = noteId))
            },
        )
    }

    private fun detailComponent(context: ComponentContext, noteId: Long?): NoteDetailState {
        return noteDetailFactory(
            componentContext = context,
            noteId = noteId,
            onBack = {
                navigation.pop()
            },
        )
    }

    // Screen parameters.
    @Serializable
    sealed interface Configuration {
        @Serializable
        data object Splash : Configuration

        @Serializable
        data object NoteList : Configuration

        @Serializable
        data class NoteDetail(
            val noteId: Long? = null,
        ) : Configuration
    }
}
