package com.example.movieskmm.domain.usecase.security

import com.example.movieskmm.BaseTest
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Ignore
@ExperimentalCoroutinesApi
class CryptUseCaseTest : BaseTest() {
    private val checkEncryptionPassUseCase = CheckEncryptionPassphraseUseCase(dbHelper)
    private val enableEncryptionUseCase = EnableEncryptionUseCase(dbHelper)

    private val dbIsEncrypted: Boolean
        get() = dbHelper.databaseState == PlatformSQLiteState.ENCRYPTED

    @BeforeTest
    fun setUp() = runTest {
        deleteDb()
    }

    @AfterTest
    fun tearDown() = runTest {
        dbHelper.closeDatabase()
    }

    @Test
    fun dbIsEncryptedTest() = runTest {
        assertFalse(dbIsEncrypted)
    }

    @Test
    fun checkPasswordTest() = runTest {
        assertTrue(checkEncryptionPassUseCase.perform(params = ""), "empty pass")
        assertFalse(checkEncryptionPassUseCase.perform(params = "~"), "not empty pass")
    }
}