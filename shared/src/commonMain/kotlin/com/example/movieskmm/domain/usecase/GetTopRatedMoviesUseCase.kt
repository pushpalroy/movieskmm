package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.domain.util.NetworkResponse
import io.github.aakira.napier.Napier

class GetTopRatedMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<NetworkResponse<MoviesList>, Unit> {
    override suspend fun perform(): NetworkResponse<MoviesList> {
        return try {
            moviesRepo.fetchTopRatedMovies()
        } catch (e: Exception) {
            Napier.e("Exception in fetching top rated movies: ${e.message}")
            NetworkResponse.Failure(e)
        }
    }
}