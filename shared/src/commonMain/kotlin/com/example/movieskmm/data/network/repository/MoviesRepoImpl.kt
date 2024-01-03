package com.example.movieskmm.data.network.repository

import com.example.movieskmm.data.network.entity.asDomain
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.model.MovieDetails
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.domain.util.NetworkResponse
import io.github.aakira.napier.Napier

class MoviesRepoImpl(
    private val moviesService: MoviesSource
) : MoviesRepo {
    override suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesService.fetchNowPlayingMovies()
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
            val response = moviesService.fetchPopularMovies()
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
            val response = moviesService.fetchTopRatedMovies()
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
            val response = moviesService.fetchMovieDetails(id)
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
}