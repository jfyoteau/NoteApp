package jfyoteau.noteapp.note.presentation.notedetail.state

import jfyoteau.noteapp.note.domain.usecase.AddNote
import jfyoteau.noteapp.note.domain.usecase.GetNote

class NoteDetailUseCase(
    val getNote: GetNote,
    val addNote: AddNote,
)
