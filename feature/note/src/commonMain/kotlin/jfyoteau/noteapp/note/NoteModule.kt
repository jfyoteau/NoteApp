package jfyoteau.noteapp.note

import jfyoteau.noteapp.note.data.dataModule
import jfyoteau.noteapp.note.domain.domainModule
import jfyoteau.noteapp.note.presentation.presentationModule

val noteModules = listOf(
    dataModule,
    domainModule,
    presentationModule,
)
