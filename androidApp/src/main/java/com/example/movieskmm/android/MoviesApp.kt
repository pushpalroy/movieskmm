package com.example.movieskmm.android

import android.app.Application
import com.example.movieskmm.di.getSharedModules
import org.koin.core.context.startKoin

class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modules = getSharedModules())
        }
    }
}
