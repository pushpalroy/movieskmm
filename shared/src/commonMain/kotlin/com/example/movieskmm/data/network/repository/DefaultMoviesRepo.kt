package com.example.movieskmm.data.network.repository

import com.example.movieskmm.data.network.dto.asDomain
import com.example.movieskmm.data.network.service.MoviesService
import com.example.movieskmm.domain.movie.MoviesRepo
import com.example.movieskmm.domain.movie.model.MoviesListDomain

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