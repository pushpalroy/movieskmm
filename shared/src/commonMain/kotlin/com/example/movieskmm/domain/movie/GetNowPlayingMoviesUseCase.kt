package com.example.movieskmm.domain.movie

import com.example.movieskmm.domain.UseCase
import com.example.movieskmm.domain.movie.model.MoviesListDomain

class GetNowPlayingMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<MoviesListDomain, Unit> {
    override suspend fun perform(): MoviesListDomain {
        return moviesRepo.fetchNowPlayingMovies()
    }
}