package com.example.domain.movie

import com.example.domain.UseCase
import com.example.domain.movie.model.MoviesListDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetNowPlayingMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<MoviesListDomain, Unit> {
    override suspend fun perform(): MoviesListDomain {
        return moviesRepo.fetchNowPlayingMovies()
    }
}