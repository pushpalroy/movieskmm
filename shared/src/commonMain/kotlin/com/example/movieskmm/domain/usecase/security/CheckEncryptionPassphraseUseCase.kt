package com.example.movieskmm.domain.usecase.security

import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import com.example.movieskmm.domain.usecase.UseCase
import io.github.aakira.napier.Napier

class CheckEncryptionPassphraseUseCase(
    private val dbHelper: DbHelper
) : UseCase<Boolean, CharSequence> {
    override suspend fun perform(params: CharSequence): Boolean {
        return try {
            if (dbHelper.databaseState == PlatformSQLiteState.ENCRYPTED) {
                //dbHelper.closeDatabase()
                val passphrase = StringBuilder(params) // threadsafe
                dbHelper.buildDbIfNeed(passphrase)
            }
            true
        } catch (t: Throwable) {
            Napier.i(message = "Incorrect passphrase", throwable = t)
            false
        }
    }
}