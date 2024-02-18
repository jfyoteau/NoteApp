package jfyoteau.noteapp.note.presentation.notelist.state

class NoteListNavigation(
    val onAddNote: () -> Unit,
    val onEditNote: (noteId: Long) -> Unit,
)
