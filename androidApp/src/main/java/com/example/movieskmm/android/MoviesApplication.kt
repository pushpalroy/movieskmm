package com.example.movieskmm.android

import android.app.Application
import com.example.movieskmm.di.initKoin
import org.koin.android.ext.koin.androidContext

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MoviesApplication)
        }
    }
}
