package com.example.movieskmm.db

import com.example.movieskmm.data.local.db.AppDatabaseDAO
import com.example.movieskmm.data.local.db.DatabaseHolder
import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import com.example.movieskmm.data.local.db.util.PlatformSQLiteThrowable

/**
 * Encryption functions are mocked
 */
class JvmTestDbHelper : DbHelper() {
    @Volatile
    private var databaseHolder: DatabaseHolder? = buildDbIfNeed()

    override val databaseState: PlatformSQLiteState
        get() = TODO("Not yet implemented")

    override val appDatabaseDAO: AppDatabaseDAO
        get() = AppDatabaseDAO(
            databaseHolder?.appDatabaseQueries ?: throw PlatformSQLiteThrowable("DB is null")
        )

    override fun buildDbIfNeed(passphrase: CharSequence): DatabaseHolder = synchronized(this) {
        var instance = databaseHolder
        if (instance == null) {
            instance = JdbcDatabaseTestHolder()
            databaseHolder = instance
        }
        return instance
    }

    override fun decrypt(oldPass: CharSequence) {
        closeDatabase()
        buildDbIfNeed()
    }

    override fun reKey(oldPass: CharSequence, newPass: CharSequence) {
        closeDatabase()
        buildDbIfNeed(newPass)
    }

    override fun encrypt(newPass: CharSequence) {
        closeDatabase()
        buildDbIfNeed(newPass)
    }

    override fun closeDatabase() = synchronized(this) {
        databaseHolder?.close()
        databaseHolder = null
    }
}