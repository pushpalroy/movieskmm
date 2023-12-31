package com.example.movieskmm.android

import android.app.Application
import com.example.movieskmm.android.di.viewModelModule
import com.example.movieskmm.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MoviesApplication)
            modules(viewModelModule)
        }
    }
}
