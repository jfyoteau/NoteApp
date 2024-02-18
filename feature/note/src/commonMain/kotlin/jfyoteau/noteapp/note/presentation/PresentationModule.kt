package jfyoteau.noteapp.note.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.note.presentation.notedetail.component.DefaultNoteDetailComponent
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailComponent
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailNavigation
import jfyoteau.noteapp.note.presentation.notedetail.component.NoteDetailUseCase
import jfyoteau.noteapp.note.presentation.notelist.component.DefaultNoteListComponent
import jfyoteau.noteapp.note.presentation.notelist.component.NoteListComponent
import jfyoteau.noteapp.note.presentation.notelist.component.NoteListNavigation
import jfyoteau.noteapp.note.presentation.notelist.component.NoteListUseCase
import org.koin.dsl.module

val presentationModule = module {
    single<NoteListComponent.Factory> {
        object: NoteListComponent.Factory {
            override operator fun invoke(
                componentContext: ComponentContext,
                onAddNote: () -> Unit,
                onEditNote: (noteId: Long) -> Unit
            ): NoteListComponent {
                return DefaultNoteListComponent(
                    componentContext = componentContext,
                    navigation = NoteListNavigation(
                        onAddNote = onAddNote,
                        onEditNote = onEditNote,
                    ),
                    useCase = NoteListUseCase(
                        getNotes = get(),
                        addNote = get(),
                        deleteNote = get(),
                    ),
                )
            }
        }
    }

    single<NoteDetailComponent.Factory> {
        object: NoteDetailComponent.Factory {
            override operator fun invoke(
                componentContext: ComponentContext,
                noteId: Long?,
                onBack: () -> Unit
            ): NoteDetailComponent {
                return DefaultNoteDetailComponent(
                    componentContext = componentContext,
                    noteId = noteId,
                    navigation = NoteDetailNavigation(
                        onBack = onBack,
                    ),
                    useCase = NoteDetailUseCase(
                        getNote = get(),
                        addNote = get(),
                    )
                )
            }
        }
    }
}
