package com.example.movieskmm.domain.usecase.security

import com.example.movieskmm.PrintAntiLog
import com.example.movieskmm.StubEditable
import com.example.movieskmm.anyObject
import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@ExperimentalCoroutinesApi
class CryptUseCaseUnitTest {
    private val dbHelper = Mockito.mock(DbHelper::class.java)
    private val checkEncryptionPassUseCase = CheckEncryptionPassphraseUseCase(dbHelper)
    private val enableEncryptionUseCase = EnableEncryptionUseCase(dbHelper)

    @BeforeTest
    fun setUp() = runTest {
        Napier.base(PrintAntiLog())
    }

    @AfterTest
    fun tearDown() = runTest {
        Napier.takeLogarithm()
    }

    @Test
    fun `check correct password when encryption enabled`() = runTest {
        val pass = StubEditable("correct password")
        Mockito.`when`(dbHelper.databaseState).thenReturn(PlatformSQLiteState.ENCRYPTED)
        assertTrue(checkEncryptionPassUseCase.perform(pass))
    }

    @Test
    fun `check incorrect password when encryption enabled`() = runTest {
        val pass = StubEditable("incorrect password")
        Mockito.`when`(dbHelper.databaseState).thenReturn(PlatformSQLiteState.ENCRYPTED)
        Mockito.`when`(dbHelper.buildDbIfNeed(anyObject())).thenThrow(RuntimeException())
        assertFalse(checkEncryptionPassUseCase.perform(pass))
    }

    @Test
    fun `encryption not enabled`() = runTest {
        val pass = StubEditable("")
        Mockito.`when`(dbHelper.databaseState).thenReturn(PlatformSQLiteState.UNENCRYPTED)
        assertTrue(checkEncryptionPassUseCase.perform(pass))
    }

    @Test
    fun `encryption happens when not encrypted`() {
        Mockito.`when`(dbHelper.databaseState).thenReturn(PlatformSQLiteState.UNENCRYPTED)
        val passPhrase = StubEditable("passphrase")
        enableEncryptionUseCase.perform(passPhrase)
        Mockito.verify(dbHelper).encrypt(passPhrase)
    }

    @Test
    fun `encryption never happens when passphrase is null`() {
        Mockito.`when`(dbHelper.databaseState).thenReturn(PlatformSQLiteState.UNENCRYPTED)
        enableEncryptionUseCase.perform(null)
        Mockito.verify(dbHelper, Mockito.never()).encrypt(anyObject())
    }
}