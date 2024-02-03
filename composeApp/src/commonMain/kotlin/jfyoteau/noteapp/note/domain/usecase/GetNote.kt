package jfyoteau.noteapp.note.domain.usecase

import jfyoteau.noteapp.note.domain.model.Note

interface GetNote {
    suspend operator fun invoke(id: Long): Note?
}
