import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import com.example.movieskmm.domain.usecase.security.CheckEncryptionPassphraseUseCase
import com.example.movieskmm.domain.usecase.security.EnableEncryptionUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject

@MediumTest
@RunWith(AndroidJUnit4::class)
class CryptUseCaseInstrumentedTest {

    private val dbHelper: DbHelper by inject(DbHelper::class.java)
    private val checkEncryptionPassUseCase = CheckEncryptionPassphraseUseCase(dbHelper)
    private val enableEncryptionUseCase = EnableEncryptionUseCase(dbHelper)
    private val password = "password"

    private val dbIsEncrypted: Boolean
        get() = dbHelper.databaseState == PlatformSQLiteState.ENCRYPTED

    @Test
    fun cryptTest() = runBlocking {
        dbHelper.buildDbIfNeed()
        assertFalse(dbIsEncrypted)
        enableEncryptionUseCase.perform(password)
        assertTrue(dbIsEncrypted)
        dbHelper.closeDatabase()
        assertFalse(checkEncryptionPassUseCase.perform("incorrect password"))
        assertTrue(checkEncryptionPassUseCase.perform(password))
    }

}