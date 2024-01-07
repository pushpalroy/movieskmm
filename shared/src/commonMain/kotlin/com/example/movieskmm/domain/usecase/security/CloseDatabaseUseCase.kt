package com.example.movieskmm.domain.usecase.security

import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.domain.usecase.UseCase
import io.github.aakira.napier.Napier

class CloseDatabaseUseCase(
    private val dbHelper: DbHelper
) : UseCase<Unit, Unit> {
    override suspend fun perform() {
        try {
            dbHelper.closeDatabase()
        } catch (t: Throwable) {
            Napier.i(message = "Error while closing database", throwable = t)
        }
    }
}