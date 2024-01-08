package com.example.movieskmm.data.local.db

import com.example.movieskmm.data.local.db.util.PlatformSQLiteState

abstract class DbHelper {
    abstract val databaseState: PlatformSQLiteState

    abstract val appDatabaseDAO: AppDatabaseDAO

    var relaunchListFlowCallback: (() -> Unit)? = null

    abstract fun buildDbIfNeed(passphrase: CharSequence = ""): DatabaseHolder

    abstract fun decrypt(oldPass: CharSequence)

    abstract fun reKey(oldPass: CharSequence, newPass: CharSequence)

    abstract fun encrypt(newPass: CharSequence)

    abstract fun closeDatabase()

    companion object {
        const val DB_NAME = "mkmm.db"
    }
}