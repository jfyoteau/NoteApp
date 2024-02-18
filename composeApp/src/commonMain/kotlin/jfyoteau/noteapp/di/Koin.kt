package jfyoteau.noteapp.di

import jfyoteau.noteapp.note.noteModules
import jfyoteau.noteapp.presentation.presentationModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinInitialization: KoinApplication.() -> Unit = {}) {
    startKoin {
        koinInitialization()
        modules(presentationModule)
        modules(noteModules)
    }
}
