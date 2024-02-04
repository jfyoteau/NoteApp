package jfyoteau.noteapp

import android.app.Application
import jfyoteau.noteapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class NoteApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@NoteApp)
            androidLogger()
        }
    }
}
