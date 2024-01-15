package com.example.movieskmm.data.local.db

import com.example.movieskmm.data.local.db.util.asFlow
import com.example.movieskmm.data.local.db.util.mapToList
import com.example.movieskmm.data.local.entity.LocalMovieEntity
import data.local.db.AppDatabaseQueries
import data.local.db.Favourite_movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Data Access Object for the notes table.
 */
class AppDatabaseDAO(private val dbQueries: AppDatabaseQueries) {
    /**
     * Select all movies from the favourite movies table.
     *
     * @return all movies.
     */
    fun getAllFavMovies(): Flow<List<Favourite_movie>> {
        return dbQueries.selectAllMovies()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .distinctUntilChanged()
    }

    /**
     * Insert a movie in the database. If the movie already exists, replace it.
     *
     * @param movie the note to be inserted.
     */
    fun insert(movie: LocalMovieEntity) {
        with(movie) {
            dbQueries.insertMovie(
                id = id.toLong(),
                title = title,
                overview = overview,
                backdropPath = backdropPath,
                posterPath = posterPath,
                voteAverage = voteAverage.toString()
            )
        }
    }

    /**
     * Delete a movie by id.
     *
     * @param id the movie id.
     */
    fun delete(id: Long) = dbQueries.deleteMovieById(id = id)

    /**
     * Delete all movie by id.
     */
    fun deleteAll() = dbQueries.deleteAllMovies()
}