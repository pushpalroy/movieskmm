package com.example.network.repository

import com.example.domain.movie.model.MoviesListDomain
import com.example.domain.movie.MoviesRepo
import com.example.network.dto.asDomain
import com.example.network.service.MoviesService

class DefaultMoviesRepo(
    private val moviesService: MoviesService
) : MoviesRepo {
    override suspend fun fetchNowPlayingMovies(): MoviesListDomain {
        return moviesService.fetchNowPlayingMovies().asDomain()
    }

    override suspend fun fetchPopularMovies(): MoviesListDomain {
        return moviesService.fetchPopularMovies().asDomain()
    }

    override suspend fun fetchTopRatedMovies(): MoviesListDomain {
        return moviesService.fetchTopRatedMovies().asDomain()
    }
}