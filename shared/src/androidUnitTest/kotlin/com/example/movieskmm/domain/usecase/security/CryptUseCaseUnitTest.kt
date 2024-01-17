package com.example.movieskmm.domain.usecase.security

import com.example.movieskmm.PrintAntiLog
import com.example.movieskmm.StubEditable
import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import io.github.aakira.napier.Napier
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@ExperimentalCoroutinesApi
class CryptUseCaseUnitTest {
    private val dbHelper = mockk<DbHelper>(relaxed = true)
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
        every { dbHelper.databaseState } returns PlatformSQLiteState.ENCRYPTED
        assertTrue(checkEncryptionPassUseCase.perform(pass))
    }

    @Test
    fun `check incorrect password when encryption enabled`() = runTest {
        val pass = StubEditable("incorrect password")
        every { dbHelper.databaseState } returns PlatformSQLiteState.ENCRYPTED
        every { dbHelper.buildDbIfNeed(any()) } throws RuntimeException()
        assertFalse(checkEncryptionPassUseCase.perform(pass))
    }

    @Test
    fun `encryption not enabled`() = runTest {
        val pass = StubEditable("")
        every { dbHelper.databaseState } returns PlatformSQLiteState.UNENCRYPTED
        assertTrue(checkEncryptionPassUseCase.perform(pass))
    }

    @Test
    fun `encryption happens when not encrypted`() {
        every { dbHelper.databaseState } returns PlatformSQLiteState.UNENCRYPTED
        val passPhrase = StubEditable("passphrase")
        enableEncryptionUseCase.perform(passPhrase)
        verify { dbHelper.encrypt(passPhrase) }
    }

    @Test
    fun `encryption never happens when passphrase is null`() {
        every { dbHelper.databaseState } returns PlatformSQLiteState.UNENCRYPTED
        enableEncryptionUseCase.perform(null)
        verify(exactly = 0) { dbHelper.encrypt(any()) }
    }
}