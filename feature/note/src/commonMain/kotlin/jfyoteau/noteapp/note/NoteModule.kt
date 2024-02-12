package jfyoteau.noteapp.note

import jfyoteau.noteapp.note.data.dataModule
import jfyoteau.noteapp.note.domain.domainModule

val noteModules = listOf(
    dataModule,
    domainModule,
)
