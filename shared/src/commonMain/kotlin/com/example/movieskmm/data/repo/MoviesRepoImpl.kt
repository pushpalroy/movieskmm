package com.example.movieskmm.data.repo

import com.example.movieskmm.data.local.entity.asDomain
import com.example.movieskmm.data.local.sources.MoviesLocalSource
import com.example.movieskmm.data.network.entity.asDomain
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.model.MovieDetails
import com.example.movieskmm.domain.model.MovieItem
import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.domain.model.asLocalEntity
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.util.NetworkResponse
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepoImpl(
    private val moviesNetworkService: MoviesSource,
    private val moviesLocalService: MoviesLocalSource
) : MoviesRepo {
    override suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesNetworkService.fetchNowPlayingMovies()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> {
                Napier.e("Error in fetching now playing movies: ${response.throwable.message}")
                return NetworkResponse.Failure(response.throwable)
            }

            is NetworkResponse.Unauthorized -> {
                Napier.e("Unauthorized in fetching now playing movies: ${response.throwable.message}")
                return NetworkResponse.Unauthorized(response.throwable)
            }
        }
    }

    override suspend fun fetchPopularMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesNetworkService.fetchPopularMovies()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> {
                Napier.e("Error in fetching popular movies: ${response.throwable.message}")
                return NetworkResponse.Failure(response.throwable)
            }

            is NetworkResponse.Unauthorized -> {
                Napier.e("Unauthorized in fetching popular movies: ${response.throwable.message}")
                return NetworkResponse.Unauthorized(response.throwable)
            }
        }
    }

    override suspend fun fetchTopRatedMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesNetworkService.fetchTopRatedMovies()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> {
                Napier.e("Error in fetching top rated movies: ${response.throwable.message}")
                return NetworkResponse.Failure(response.throwable)
            }

            is NetworkResponse.Unauthorized -> {
                Napier.e("Unauthorized in fetching top rated movies: ${response.throwable.message}")
                return NetworkResponse.Unauthorized(response.throwable)
            }
        }
    }

    override suspend fun fetchMoviesDetails(id: Int): NetworkResponse<MovieDetails> {
        return when (
            val response = moviesNetworkService.fetchMovieDetails(id)
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> {
                Napier.e("Error in fetching movie details: ${response.throwable.message}")
                return NetworkResponse.Failure(response.throwable)
            }

            is NetworkResponse.Unauthorized -> {
                Napier.e("Unauthorized in fetching movie details: ${response.throwable.message}")
                return NetworkResponse.Unauthorized(response.throwable)
            }
        }
    }

    override fun addMovieToFavourites(movie: MovieItem) {
        moviesLocalService.addMovieToFav(movie.asLocalEntity())
    }

    override fun deleteMovieFromFavourites(movieId: Int) {
        moviesLocalService.deleteFavMovie(id = movieId)
    }

    override fun getAllFavouriteMovies(): Flow<List<MovieItem>> {
        return moviesLocalService.getAllFavMovies().map { listOfItems ->
            listOfItems.map {
                it.asDomain()
            }
        }
    }
}