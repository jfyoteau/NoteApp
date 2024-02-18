package jfyoteau.noteapp.note.presentation.notelist.component

class NoteListNavigation(
    val onAddNote: () -> Unit,
    val onEditNote: (noteId: Long) -> Unit,
)
