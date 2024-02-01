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
    private val appDatabase = dbHelper.buildDbIfNeed().appDatabase
    private val movies = listOf(
        TestSchema.firstMovie, TestSchema.secondMovie, TestSchema.thirdMovie
    )

    @BeforeTest
    fun setUp() = runTest {
        println("Setup done")
        appDatabase.appDatabaseQueries.transaction {
            movies.forEach {
                println("Inserting movie: ${it.id}")
                appDatabase.appDatabaseQueries.insetEachMovie(it)
            }
        }
    }

    @AfterTest
    fun tearDown() = runTest {
        println("Tear down")
        appDatabase.appDatabaseQueries.transaction {
            println("Deleting all movies")
            appDatabase.appDatabaseQueries.deleteAllMovies()
        }
    }

    @Test
    fun should_fetch_all_inserted_movies() = runTest {
        println("Executing should_fetch_all_inserted_movies")
        val moviesFlow = appDatabaseDAO.getAllFavMovies()
        assertEquals(movies, moviesFlow.first())
    }

    @Test
    fun should_insert_new_item_correctly() = runTest {
        println("Executing should_insert_new_item_correctly")
        appDatabaseDAO.insert(
            LocalMovieEntity(
                40, "Abc", "Xyz", "/abc", "/xyz", 1.1
            )
        )
        val favouriteMovie = appDatabaseDAO.getAllFavMovies().first().last()
        assertEquals(40, favouriteMovie.id)
        assertEquals("Abc", favouriteMovie.title)
        assertEquals("Xyz", favouriteMovie.overview)
        assertEquals("/abc", favouriteMovie.backdropPath)
        assertEquals("/xyz", favouriteMovie.posterPath)
        assertEquals("1.1", favouriteMovie.voteAverage)
    }
}