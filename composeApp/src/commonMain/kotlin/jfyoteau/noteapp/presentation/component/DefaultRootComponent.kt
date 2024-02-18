package jfyoteau.noteapp.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailComponent
import jfyoteau.noteapp.note.presentation.notelist.component.NoteListComponent
import jfyoteau.noteapp.splash.presentation.component.SplashComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val splashFactory: SplashComponent.Factory,
    private val noteListFactory: NoteListComponent.Factory,
    private val noteDetailFactory: NoteDetailComponent.Factory,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Splash,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        configuration: Configuration,
        context: ComponentContext,
    ): RootComponent.Child {
        return when (configuration) {
            is Configuration.Splash -> RootComponent.Child.Splash(
                splashComponent(context)
            )

            is Configuration.NoteList -> RootComponent.Child.NoteList(
                listComponent(context)
            )

            is Configuration.NoteDetail -> RootComponent.Child.NoteDetail(
                detailComponent(context, configuration.noteId)
            )
        }
    }

    private fun splashComponent(context: ComponentContext): SplashComponent {
        return splashFactory(
            componentContext = context,
            onCompleted = {
                navigation.replaceAll(Configuration.NoteList)
            }
        )
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun listComponent(context: ComponentContext): NoteListComponent {
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

    private fun detailComponent(context: ComponentContext, noteId: Long?): NoteDetailComponent {
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
