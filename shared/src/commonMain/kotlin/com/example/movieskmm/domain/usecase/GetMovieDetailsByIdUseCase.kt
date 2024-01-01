package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.model.MovieDetails
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.util.NetworkResponse

class GetMovieDetailsByIdUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<NetworkResponse<MovieDetails>, Int> {
    override suspend fun perform(id: Int): NetworkResponse<MovieDetails> {
        return moviesRepo.fetchMoviesDetails(id)
    }
}