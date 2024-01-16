import android.text.SpannableStringBuilder
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.util.PlatformSQLiteState
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject

@MediumTest
@RunWith(AndroidJUnit4::class)
class CryptInstrumentedTest {

    private val dbHelper: DbHelper by inject(DbHelper::class.java)
    private val password = "password"

    @Test
    fun cryptTest() {
        assertEquals(PlatformSQLiteState.DOES_NOT_EXIST, dbHelper.databaseState)
        dbHelper.buildDbIfNeed()
        assertEquals(PlatformSQLiteState.UNENCRYPTED, dbHelper.databaseState)
        dbHelper.encrypt(SpannableStringBuilder(password))
        assertEquals(PlatformSQLiteState.ENCRYPTED, dbHelper.databaseState)
        dbHelper.decrypt(SpannableStringBuilder(password))
        assertEquals(PlatformSQLiteState.UNENCRYPTED, dbHelper.databaseState)
    }
}