package jfyoteau.noteapp.note.presentation

import com.arkivanov.decompose.ComponentContext
import jfyoteau.noteapp.note.presentation.notedetail.state.DefaultNoteDetailState
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailState
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailNavigation
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailUseCase
import jfyoteau.noteapp.note.presentation.notelist.state.DefaultNoteListState
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListState
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListNavigation
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListUseCase
import org.koin.dsl.module

val presentationModule = module {
    single<NoteListState.Factory> {
        object: NoteListState.Factory {
            override operator fun invoke(
                componentContext: ComponentContext,
                onAddNote: () -> Unit,
                onEditNote: (noteId: Long) -> Unit
            ): NoteListState {
                return DefaultNoteListState(
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

    single<NoteDetailState.Factory> {
        object: NoteDetailState.Factory {
            override operator fun invoke(
                componentContext: ComponentContext,
                noteId: Long?,
                onBack: () -> Unit
            ): NoteDetailState {
                return DefaultNoteDetailState(
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
