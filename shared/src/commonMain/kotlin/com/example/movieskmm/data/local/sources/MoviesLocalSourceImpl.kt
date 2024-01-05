package com.example.movieskmm.data.local.sources

import com.example.movieskmm.data.local.db.AppDatabaseDAO
import com.example.movieskmm.data.local.entity.LocalMovieEntity
import data.local.db.Favourite_movie
import kotlinx.coroutines.flow.Flow

class MoviesLocalSourceImpl(private val dao: AppDatabaseDAO) : MoviesLocalSource {

    override fun addMovieToFav(movie: LocalMovieEntity) {
        dao.insert(movie)
    }

    override fun getAllFavMovies(): Flow<List<Favourite_movie>> {
        return dao.getAllFavMovies()
    }

    override fun deleteFavMovie(id: Int) {
        dao.delete(id.toLong())
    }
}