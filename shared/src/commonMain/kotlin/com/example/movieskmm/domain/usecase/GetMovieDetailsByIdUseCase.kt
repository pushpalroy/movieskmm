package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.model.MovieDetails
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.util.NetworkResponse
import io.github.aakira.napier.Napier

class GetMovieDetailsByIdUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<NetworkResponse<MovieDetails>, Int> {
    override suspend fun perform(params: Int): NetworkResponse<MovieDetails> {
        return try {
            moviesRepo.fetchMoviesDetails(params)
        } catch (e: Exception) {
            Napier.e("Exception in fetching movie details: ${e.message}")
            NetworkResponse.Failure(e)
        }
    }
}