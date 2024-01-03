package com.example.movieskmm.di

import com.example.movieskmm.data.local.db.DriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        DriverFactory().createDriver()
    }
}

