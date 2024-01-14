package com.example.movieskmm.data.local.db

import com.example.movieskmm.BaseTest
import com.example.movieskmm.data.local.db.TestSchema.insetEachMovie
import com.example.movieskmm.data.local.entity.LocalMovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AppDatabaseDaoTest : BaseTest() {

    private val appDatabaseDAO = dbHelper.appDatabaseDAO

    private val movies = listOf(
        TestSchema.firstMovie, TestSchema.secondMovie, TestSchema.thirdMovie
    )

    @BeforeTest
    fun setUp() = runTest {
        val appDatabase = dbHelper.buildDbIfNeed().appDatabase
        appDatabase.appDatabaseQueries.transaction {
            movies.forEach {
                appDatabase.appDatabaseQueries.insetEachMovie(it)
            }
        }
    }

    @AfterTest
    fun tearDown() = runTest {
        val appDatabase = dbHelper.buildDbIfNeed().appDatabase
        appDatabase.appDatabaseQueries.transaction {
            movies.forEach {
                appDatabase.appDatabaseQueries.deleteMovieById(it.id)
            }
        }
    }

    @Test
    fun should_fetch_all_inserted_movies() = runTest {
        assertEquals(movies, appDatabaseDAO.getAllFavMovies().first())
    }

    @Test
    fun should_insert_new_item_correctly() = runTest {
        appDatabaseDAO.insert(
            LocalMovieEntity(
                4, "Abc", "Xyz", "/abc", "/xyz", 1.1
            )
        )
        val favouriteMovie = appDatabaseDAO.getAllFavMovies().first().last()
        assertEquals(4, favouriteMovie.id)
        assertEquals("Abc", favouriteMovie.title)
        assertEquals("Xyz", favouriteMovie.overview)
        assertEquals("/abc", favouriteMovie.backdropPath)
        assertEquals("/xyz", favouriteMovie.posterPath)
        assertEquals("1.1", favouriteMovie.voteAverage)
    }
}