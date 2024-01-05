package com.example.movieskmm.di

import com.example.movieskmm.data.local.db.AndroidDbHelper
import com.example.movieskmm.data.local.db.DbHelper
import org.koin.dsl.module

actual fun platformModule() = module {
    single<DbHelper> { AndroidDbHelper(get()) }
}