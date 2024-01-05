package com.example.movieskmm.data.local.db

import com.example.movieskmm.data.local.db.util.PlatformSQLiteState

class IosDbHelper : DbHelper() {

    private var dbHolder: DatabaseHolder? = null

    override val databaseState: PlatformSQLiteState
        get() = IosCipherUtils.getDatabaseState(DB_NAME)

    override val appDatabaseDAO: AppDatabaseDAO
        get() = AppDatabaseDAO(buildDbIfNeed().appDatabaseQueries)

    override fun buildDbIfNeed(passphrase: CharSequence): DatabaseHolder {
        var instance = dbHolder
        if (instance == null) {
            val passCopy: String? = if (passphrase.isNotEmpty()) passphrase.toString() else null
            instance = IosDatabaseHolder(key = passCopy)
            dbHolder = instance
        }
        return instance
    }

    override fun decrypt(oldPass: CharSequence) {
        closeDatabase()
        IosCipherUtils.decrypt(oldPass.toString(), DB_NAME)
        dbHolder = IosDatabaseHolder()
    }

    override fun reKey(oldPass: CharSequence, newPass: CharSequence) {
        closeDatabase()
        dbHolder = IosDatabaseHolder(key = oldPass.toString(), reKey = newPass.toString())
        dbHolder?.driver?.execute(null, "VACUUM;", 0)
    }

    override fun encrypt(newPass: CharSequence) {
        closeDatabase()
        IosCipherUtils.encrypt(newPass.toString(), DB_NAME)
        dbHolder = IosDatabaseHolder(key = newPass.toString())
    }

    override fun closeDatabase() {
        dbHolder?.close()
        dbHolder = null
    }
}