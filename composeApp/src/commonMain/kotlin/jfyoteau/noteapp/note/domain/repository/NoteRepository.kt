package jfyoteau.noteapp.note.domain.repository

import jfyoteau.noteapp.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): Flow<List<Note>>
    suspend fun deleteNote(note: Note)
}
