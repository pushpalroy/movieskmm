package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesListResponse
import com.example.movieskmm.domain.util.NetworkResponse

class GetNowPlayingMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<NetworkResponse<MoviesListResponse>, Unit> {
    override suspend fun perform(): NetworkResponse<MoviesListResponse> {
        return moviesRepo.fetchNowPlayingMovies()
    }
}