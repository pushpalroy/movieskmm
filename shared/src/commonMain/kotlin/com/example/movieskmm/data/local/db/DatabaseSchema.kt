package com.example.movieskmm.data.local.db

import app.cash.sqldelight.db.SqlDriver

fun createQueryWrapper(sqlDriver: SqlDriver): AppDatabase {
    return AppDatabase(driver = sqlDriver)
}