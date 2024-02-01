package com.example.movieskmm

import com.example.movieskmm.data.local.db.DbHelper.Companion.DB_NAME
import com.example.movieskmm.data.local.db.IosCipherUtils
import com.example.movieskmm.data.local.db.IosDbHelper
import com.example.movieskmm.data.local.db.TestSchema
import com.example.movieskmm.data.local.db.TestSchema.insetEachMovie
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.Foundation.NSFileManager
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CipherInstrumentedTest {

    @BeforeTest
    fun setUp() {
        Napier.base(DebugAntilog())
        Napier.i("set-up")
    }

    @AfterTest
    fun tearDown() {
        Napier.i("tear-down")
        Napier.takeLogarithm()
    }

    @Test
    fun cryptTest() {
        Napier.v("0️⃣'th step - prepare database")
        val dbPath = IosCipherUtils.getDatabasePath(dbName = DB_NAME)
        Napier.d("dbPath: $dbPath")
        val dbExisted = NSFileManager.defaultManager.fileExistsAtPath(dbPath)
        val dbDeleted = IosCipherUtils.deleteDatabase()
        assertEquals(dbExisted, dbDeleted, message = "must be deleted if existed")

        val dbHelper = IosDbHelper()
        assertEquals(expected = PlatformSQLiteState.DOES_NOT_EXIST, actual = dbHelper.databaseState)

        val movies = listOf(TestSchema.firstMovie, TestSchema.secondMovie, TestSchema.thirdMovie)
        movies.forEach {
            dbHelper.buildDbIfNeed().appDatabaseQueries.insetEachMovie(it)
        }
        assertEquals(expected = PlatformSQLiteState.UNENCRYPTED, actual = dbHelper.databaseState)

        var moviesList =
            dbHelper.buildDbIfNeed().appDatabaseQueries.selectAllMovies().executeAsList()
        assertEquals(expected = 3, actual = moviesList.size)

        val cipherVersion = IosCipherUtils.checkCipherVersion(dbName = DB_NAME)
        assertEquals(expected = "4.5.5 community", actual = cipherVersion)

        Napier.v("1️⃣'st step - encrypt database")
        dbHelper.closeDatabase()
        val password = "password"
        IosCipherUtils.encrypt(password, DB_NAME)
        assertEquals(expected = PlatformSQLiteState.ENCRYPTED, actual = dbHelper.databaseState)

        assertTrue(actual = IosCipherUtils.checkKey(password, DB_NAME))

        Napier.v("Password checked, try to check the data.")
        var dbHolder = dbHelper.buildDbIfNeed(password)
        moviesList = dbHolder.appDatabaseQueries.selectAllMovies().executeAsList()
        assertEquals(expected = 3, actual = moviesList.size)

        Napier.v("2️⃣'nd step - change password")
        dbHelper.closeDatabase()
        val newPassword = "newPassword"
        dbHelper.reKey(password, newPassword)
        dbHelper.closeDatabase()
        assertTrue(actual = IosCipherUtils.checkKey(newPassword, DB_NAME))
        assertFalse(actual = IosCipherUtils.checkKey(password, DB_NAME))

        Napier.v("New password checked, try to check the data.")
        dbHolder = dbHelper.buildDbIfNeed(newPassword)
        moviesList = dbHolder.appDatabaseQueries.selectAllMovies().executeAsList()
        assertEquals(expected = 3, actual = moviesList.size)

        Napier.v("3️⃣'rd step - decrypt database")
        dbHelper.closeDatabase()
        IosCipherUtils.decrypt(newPassword, DB_NAME)
        assertEquals(expected = PlatformSQLiteState.UNENCRYPTED, actual = dbHelper.databaseState)

        Napier.v("Password removed, try to check the data.")
        dbHolder = dbHelper.buildDbIfNeed()
        moviesList = dbHolder.appDatabaseQueries.selectAllMovies().executeAsList()
        assertEquals(expected = 3, actual = moviesList.size)
    }
}