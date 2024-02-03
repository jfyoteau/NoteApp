package jfyoteau.noteapp.note.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.DefaultNoteDetailComponent
import jfyoteau.noteapp.note.presentation.notelist.DefaultNoteListComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.NoteList,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        configuration: Configuration,
        context: ComponentContext,
    ): RootComponent.Child {
        return when (configuration) {
            is Configuration.NoteList -> RootComponent.Child.NoteList(
                DefaultNoteListComponent(
                    componentContext = context,
                    onAddNote = {
                        navigation.pushNew(Configuration.NoteDetail())
                    },
                    onEditNote = { noteId ->
                        navigation.pushNew(Configuration.NoteDetail(noteId = noteId))
                    },
                )
            )

            is Configuration.NoteDetail -> RootComponent.Child.NoteDetail(
                DefaultNoteDetailComponent(
                    noteId = configuration.noteId,
                    componentContext = context,
                    onBack = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    // Screen parameters.
    @Serializable
    sealed interface Configuration {
        @Serializable
        data object NoteList : Configuration

        @Serializable
        data class NoteDetail(
            val noteId: Long? = null,
        ) : Configuration
    }
}
