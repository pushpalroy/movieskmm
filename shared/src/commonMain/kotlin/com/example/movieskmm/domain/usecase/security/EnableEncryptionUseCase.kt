package com.example.movieskmm.domain.usecase.security

import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import io.github.aakira.napier.Napier

class EnableEncryptionUseCase(
    private val dbHelper: DbHelper
) {
    fun perform(params: CharSequence?) {
        try {
            if (dbHelper.databaseState != PlatformSQLiteState.ENCRYPTED) {
                requireNotNull(params)
                dbHelper.encrypt(params)
            }
        } catch (e: Exception) {
            Napier.i(message = "Exception in encrypting database", throwable = e)
        }
        dbHelper.relaunchListFlowCallback?.invoke()
    }
}