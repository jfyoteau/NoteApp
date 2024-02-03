package jfyoteau.noteapp.di

import jfyoteau.noteapp.note.noteModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinInitialization: KoinApplication.() -> Unit = {}) {
    startKoin {
        koinInitialization()
        modules(noteModules)
    }
}
