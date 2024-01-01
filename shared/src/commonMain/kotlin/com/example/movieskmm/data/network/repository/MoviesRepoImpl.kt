package com.example.movieskmm.data.network.repository

import com.example.movieskmm.data.network.entity.asDomain
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.model.MovieDetails
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.domain.util.NetworkResponse

class MoviesRepoImpl(
    private val moviesService: MoviesSource
) : MoviesRepo {
    override suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesService.fetchNowPlayingMovies()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> NetworkResponse.Failure(response.throwable)
            is NetworkResponse.Unauthorized -> NetworkResponse.Unauthorized(response.throwable)
        }
    }

    override suspend fun fetchPopularMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesService.fetchPopularMovies()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> NetworkResponse.Failure(response.throwable)
            is NetworkResponse.Unauthorized -> NetworkResponse.Unauthorized(response.throwable)
        }
    }

    override suspend fun fetchTopRatedMovies(): NetworkResponse<MoviesList> {
        return when (
            val response = moviesService.fetchTopRatedMovies()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> NetworkResponse.Failure(response.throwable)
            is NetworkResponse.Unauthorized -> NetworkResponse.Unauthorized(response.throwable)
        }
    }

    override suspend fun fetchMoviesDetails(id: Int): NetworkResponse<MovieDetails> {
        return when (
            val response = moviesService.fetchMovieDetails(id)
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data.asDomain())
            is NetworkResponse.Failure -> NetworkResponse.Failure(response.throwable)
            is NetworkResponse.Unauthorized -> NetworkResponse.Unauthorized(response.throwable)
        }
    }
}