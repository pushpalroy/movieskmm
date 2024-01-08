package com.example.movieskmm.data.local.db

import app.cash.sqldelight.db.SqlDriver
import data.local.db.AppDatabaseQueries

abstract class DatabaseHolder {
    abstract val driver: SqlDriver
    abstract val appDatabase: AppDatabase
    abstract val appDatabaseQueries: AppDatabaseQueries

    abstract fun close()
}