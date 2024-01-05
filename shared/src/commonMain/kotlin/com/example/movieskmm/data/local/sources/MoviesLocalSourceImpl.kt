package com.example.movieskmm.data.local.sources

import com.example.movieskmm.data.local.db.AppDatabase
import com.example.movieskmm.data.local.db.util.asFlow
import com.example.movieskmm.data.local.db.util.mapToList
import com.example.movieskmm.data.local.entity.LocalMovieEntity
import app.cash.sqldelight.db.SqlDriver
import data.local.db.Favourite_movie
import kotlinx.coroutines.flow.Flow

class MoviesLocalSourceImpl(override var driver: SqlDriver?) : MoviesLocalSource {

    private val database by lazy { AppDatabase(driver!!) }
    private val dbQuery by lazy { database.appDatabaseQueries }

    override fun addMovieToFav(movie: LocalMovieEntity) {
        with(movie) {
            dbQuery.insertMovie(
                id = id.toLong(),
                title = title,
                overview = overview,
                backdropPath = backdropPath,
                posterPath = posterPath,
                voteAverage = voteAverage.toString()
            )
        }
    }

    override fun getAllFavMovies(): Flow<List<Favourite_movie>> {
        return dbQuery.selectAllMovies().asFlow().mapToList()
    }

    override fun deleteFavMovie(id: Int) {
        dbQuery.deleteMovieById(id.toLong())
    }
}