package com.example.movieskmm.data.local.db

import app.cash.sqldelight.db.SqlDriver
import data.local.db.AppDatabaseQueries
import data.local.db.Favourite_movie

fun createQueryWrapper(sqlDriver: SqlDriver): AppDatabase {
    return AppDatabase(driver = sqlDriver)
}

object TestSchema {

    val firstMovie = Favourite_movie(
        id = 10,
        title = "first movie from test schema",
        overview = "first overview",
        backdropPath = "first backdropPath",
        posterPath = "first posterPath",
        voteAverage = "1.1"
    )
    val secondMovie = Favourite_movie(
        id = 20,
        title = "second movie from test schema",
        overview = "second overview",
        backdropPath = "second backdropPath",
        posterPath = "second posterPath",
        voteAverage = "1.2"
    )
    val thirdMovie = Favourite_movie(
        id = 30,
        title = "third movie from test schema",
        overview = "third overview",
        backdropPath = "third backdropPath",
        posterPath = "third posterPath",
        voteAverage = "1.3"
    )

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
