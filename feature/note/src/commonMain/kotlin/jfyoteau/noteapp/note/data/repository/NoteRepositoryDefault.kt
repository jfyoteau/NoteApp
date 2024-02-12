package jfyoteau.noteapp.note.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import jfyoteau.noteapp.note.data.database.NoteQueries
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class NoteRepositoryDefault(
    private val noteQueries: NoteQueries
) : NoteRepository {
    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun insertNote(note: Note) {
        noteQueries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            color = note.color.toHexString(HexFormat.UpperCase),
            created_at = note.createdAt,
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun getNoteById(id: Long): Note? {
        return noteQueries
            .getNoteById(id) { noteId, title, content, color, created ->
                Note(
                    id = noteId,
                    title = title,
                    content = content,
                    color = color.hexToLong(HexFormat.UpperCase),
                    createdAt = created,
                )
            }
            .executeAsOneOrNull()
    }

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun getAllNotes(): Flow<List<Note>> {
        return noteQueries
            .getAllNotes { id, title, content, color, created ->
                Note(
                    id = id,
                    title = title,
                    content = content,
                    color = color.hexToLong(HexFormat.UpperCase),
                    createdAt = created,
                )
            }
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    override suspend fun deleteNote(note: Note) {
        val noteId = note.id ?: return
        noteQueries.deleteNoteById(noteId)
    }
}
