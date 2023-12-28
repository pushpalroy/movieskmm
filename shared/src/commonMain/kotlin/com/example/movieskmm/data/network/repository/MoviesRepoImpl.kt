package com.example.movieskmm.data.network.repository

import com.example.movieskmm.data.network.entity.asDomain
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.model.MoviesListResponse

class MoviesRepoImpl(
    private val moviesService: MoviesSource
) : MoviesRepo {
    override suspend fun fetchNowPlayingMovies(): MoviesListResponse {
        return moviesService.fetchNowPlayingMovies().asDomain()
    }

    override suspend fun fetchPopularMovies(): MoviesListResponse {
        return moviesService.fetchPopularMovies().asDomain()
    }

    override suspend fun fetchTopRatedMovies(): MoviesListResponse {
        return moviesService.fetchTopRatedMovies().asDomain()
    }
}