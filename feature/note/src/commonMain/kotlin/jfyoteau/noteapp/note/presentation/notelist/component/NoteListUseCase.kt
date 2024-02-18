package jfyoteau.noteapp.note.presentation.notelist.component

import jfyoteau.noteapp.note.domain.usecase.AddNote
import jfyoteau.noteapp.note.domain.usecase.DeleteNote
import jfyoteau.noteapp.note.domain.usecase.GetNotes

class NoteListUseCase(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
)
