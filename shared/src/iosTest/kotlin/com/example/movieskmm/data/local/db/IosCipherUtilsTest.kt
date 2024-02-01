package com.example.movieskmm.data.local.db

import com.example.movieskmm.BaseTest
import com.example.movieskmm.data.local.db.DbHelper.Companion.DB_NAME
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import com.example.movieskmm.domain.usecase.security.CheckEncryptionPassphraseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import platform.Foundation.NSFileManager
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class IosCipherUtilsTest : BaseTest() {

    @Test
    fun getDatabaseStateTest() {
        IosCipherUtils.deleteDatabase()
        var exp = PlatformSQLiteState.DOES_NOT_EXIST
        var act = IosCipherUtils.getDatabaseState(DB_NAME)
        assertEquals(exp, act)

        IosDatabaseHolder().close()
        exp = PlatformSQLiteState.DOES_NOT_EXIST
        act = IosCipherUtils.getDatabaseState(DB_NAME)
        assertEquals(exp, act)

        IosCipherUtils.deleteDatabase()
        IosDatabaseHolder(key = "password").close()
        exp = PlatformSQLiteState.DOES_NOT_EXIST
        act = IosCipherUtils.getDatabaseState(DB_NAME)
        assertEquals(exp, act)
    }

    @Test
    @Ignore
    fun checkKeyTest() = runTest {
        IosCipherUtils.deleteDatabase()
        IosDatabaseHolder().close()

        var exp = true
        var act = IosCipherUtils.checkKey(null, DB_NAME)
        assertEquals(exp, act, "null pass")

        IosCipherUtils.deleteDatabase()
        IosDatabaseHolder(key = "password").close()

        exp = false
        act = IosCipherUtils.checkKey("incorrect password", DB_NAME)
        assertEquals(exp, act, "incorrect pass")

        exp = true
        val checkEncryptionPassphraseUseCase = CheckEncryptionPassphraseUseCase(dbHelper)
        act = checkEncryptionPassphraseUseCase.perform("password")
        assertEquals(exp, act, "correct pass")
    }

    @Test
    @Ignore
    fun deleteDatabaseTest() {
        val dbPath = IosCipherUtils.getDatabasePath(DB_NAME)
        val exp = NSFileManager.defaultManager.fileExistsAtPath(dbPath)
        val act = IosCipherUtils.deleteDatabase()
        assertEquals(exp, act)
    }
}