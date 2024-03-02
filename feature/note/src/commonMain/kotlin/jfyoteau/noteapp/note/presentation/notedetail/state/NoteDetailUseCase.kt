package jfyoteau.noteapp.note.presentation.notedetail.state

import jfyoteau.noteapp.note.domain.usecase.AddNoteUseCase
import jfyoteau.noteapp.note.domain.usecase.GetNoteUseCase

class NoteDetailUseCase(
    val getNoteUseCase: GetNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
)
