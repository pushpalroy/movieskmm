import android.content.Context
import android.text.Editable
import android.text.SpannableStringBuilder
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.commonsware.cwac.saferoom.SQLCipherUtils
import com.commonsware.cwac.saferoom.SafeHelperFactory
import com.example.movieskmm.data.local.db.AppDatabase
import com.example.movieskmm.data.local.db.DbHelper.Companion.DB_NAME
import com.example.movieskmm.data.local.db.TestSchema.firstMovie
import com.example.movieskmm.data.local.db.TestSchema.insetEachMovie
import com.example.movieskmm.data.local.db.TestSchema.secondMovie
import com.example.movieskmm.data.local.db.TestSchema.thirdMovie
import com.example.movieskmm.data.local.db.createQueryWrapper
import data.local.db.Favourite_movie
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class SqlDelightCipherInstrumentedTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val databaseState: SQLCipherUtils.State
        get() = SQLCipherUtils.getDatabaseState(context, DB_NAME)

    @Test
    fun primitiveTest() {
        assertEquals(SQLCipherUtils.State.DOES_NOT_EXIST, databaseState)

        val emptyPassword: Editable = SpannableStringBuilder.valueOf("")
        val callback: SupportSQLiteOpenHelper.Callback =
            AndroidSqliteDriver.Callback(AppDatabase.Schema)
        var openDatabase: SupportSQLiteDatabase = SafeHelperFactory
            .fromUser(emptyPassword)
            .create(context, DB_NAME, callback)
            .writableDatabase

        assertEquals(SQLCipherUtils.State.UNENCRYPTED, databaseState)

        var driver: SqlDriver = AndroidSqliteDriver(openDatabase)
        var appDatabaseQueries = createQueryWrapper(driver).appDatabaseQueries

        // ZERO STEP
        val movies = listOf(firstMovie, secondMovie, thirdMovie)
        movies.forEach {
            appDatabaseQueries.insetEachMovie(it)
        }
        var exp = movies.sortedBy(Favourite_movie::id)
        val act = appDatabaseQueries.selectAllMovies().executeAsList().sortedBy(Favourite_movie::id)
        assertEquals(exp, act)

        SQLCipherUtils.encrypt(context, DB_NAME, "password".toCharArray())// db close inside
        assertEquals(SQLCipherUtils.State.ENCRYPTED, databaseState)

        val password = SpannableStringBuilder.valueOf("password")
        openDatabase = SafeHelperFactory
            .fromUser(password)
            .create(context, DB_NAME, callback)
            .writableDatabase
        driver = AndroidSqliteDriver(openDatabase)
        appDatabaseQueries = createQueryWrapper(driver).appDatabaseQueries

        // FIRST STEP
        appDatabaseQueries.deleteMovieById(firstMovie.id)
        exp = listOf(secondMovie, thirdMovie)
        assertEquals(exp, appDatabaseQueries.selectAllMovies().executeAsList())

        SafeHelperFactory.rekey(openDatabase, "new password".toCharArray())

        // SECOND STEP
        appDatabaseQueries.deleteMovieById(secondMovie.id)
        exp = listOf(thirdMovie)
        assertEquals(exp, appDatabaseQueries.selectAllMovies().executeAsList())

        val originalFile = context.getDatabasePath(DB_NAME)
        SQLCipherUtils.decrypt(context, originalFile, "new password".toCharArray())
        assertEquals(SQLCipherUtils.State.UNENCRYPTED, databaseState)

        openDatabase = SafeHelperFactory
            .fromUser(emptyPassword)
            .create(context, DB_NAME, callback)
            .writableDatabase
        driver = AndroidSqliteDriver(openDatabase)
        appDatabaseQueries = createQueryWrapper(driver).appDatabaseQueries

        // THIRD STEP
        appDatabaseQueries.deleteMovieById(thirdMovie.id)
        exp = listOf()
        assertEquals(exp, appDatabaseQueries.selectAllMovies().executeAsList())
    }
}