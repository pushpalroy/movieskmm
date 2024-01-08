package com.example.movieskmm.di

import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.IosDbHelper
import org.koin.dsl.module

actual fun platformModule() = module {
    single<DbHelper> { IosDbHelper() }
}