package com.example.movieskmm.db

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.movieskmm.data.local.db.AppDatabase
import com.example.movieskmm.data.local.db.DatabaseHolder
import com.example.movieskmm.data.local.db.createQueryWrapper

class JdbcDatabaseTestHolder : DatabaseHolder() {
    override val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    override val appDatabase: AppDatabase = createQueryWrapper(driver)
    override val appDatabaseQueries = appDatabase.appDatabaseQueries

    init {
        AppDatabase.Schema.create(driver)
    }

    override fun close() = driver.close()
}