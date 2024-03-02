package jfyoteau.noteapp.note.presentation.notelist.state

import jfyoteau.noteapp.note.domain.usecase.AddNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.DeleteNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.GetNotesUseCase

class NoteListUseCase(
    val getNotesUseCase: GetNotesUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
)
