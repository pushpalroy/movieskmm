package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.domain.util.NetworkResponse

class GetNowPlayingMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<NetworkResponse<MoviesList>, Unit> {
    override suspend fun perform(): NetworkResponse<MoviesList> {
        return moviesRepo.fetchNowPlayingMovies()
    }
}