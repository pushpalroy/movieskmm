package com.example.movieskmm.data.local.sources

import com.example.movieskmm.data.local.entity.LocalMovieEntity
import com.squareup.sqldelight.db.SqlDriver
import data.local.db.Favourite_movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalSource {
    var driver: SqlDriver?
    fun addMovieToFav(movie: LocalMovieEntity)
    fun getAllFavMovies(): Flow<List<Favourite_movie>>
    fun deleteFavMovie(id: Int)
}