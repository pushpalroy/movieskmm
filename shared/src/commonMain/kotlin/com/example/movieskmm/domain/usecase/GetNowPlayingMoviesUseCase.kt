package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesListResponse

class GetNowPlayingMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<MoviesListResponse, Unit> {
    override suspend fun perform(): MoviesListResponse {
        return moviesRepo.fetchNowPlayingMovies()
    }
}