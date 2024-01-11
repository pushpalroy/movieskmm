package com.example.movieskmm.data.local.db

import app.cash.sqldelight.db.SqlDriver
import data.local.db.AppDatabaseQueries
import data.local.db.Favourite_movie

fun createQueryWrapper(sqlDriver: SqlDriver): AppDatabase {
    return AppDatabase(driver = sqlDriver)
}

object TestSchema {

    val firstMovie = Favourite_movie(
        id = 1,
        title = "first movie from test schema",
        overview = "first overview",
        backdropPath = "first backdropPath",
        posterPath = "first posterPath",
        voteAverage = "1.1"
    )
    val secondMovie = Favourite_movie(
        id = 2,
        title = "second movie from test schema",
        overview = "second overview",
        backdropPath = "second backdropPath",
        posterPath = "second posterPath",
        voteAverage = "1.2"
    )
    val thirdMovie = Favourite_movie(
        id = 3,
        title = "third movie from test schema",
        overview = "third overview",
        backdropPath = "third backdropPath",
        posterPath = "third posterPath",
        voteAverage = "1.3"
    )
    val movies = listOf(firstMovie, secondMovie, thirdMovie)

    fun insertTestMovies(appDatabaseQueries: AppDatabaseQueries) =
        movies.forEach { localMovie ->
            appDatabaseQueries.insetEachMovie(localMovie)
        }

    fun AppDatabaseQueries.insetEachMovie(favouriteMovie: Favourite_movie) {
        with(favouriteMovie) {
            insertMovie(
                id,
                title,
                overview,
                backdropPath,
                posterPath,
                voteAverage.toString()
            )
        }
    }
}
