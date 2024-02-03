package jfyoteau.noteapp.note.domain.model

import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val color: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        val noteColors = listOf(RedOrange, RedPink, LightGreen, BabyBlue, Violet)

        fun generateRandomColor() = noteColors.random()
    }
}
