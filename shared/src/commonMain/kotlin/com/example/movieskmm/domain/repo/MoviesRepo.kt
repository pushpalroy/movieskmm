package com.example.movieskmm.domain.repo

import com.example.movieskmm.domain.model.MoviesListResponse

interface MoviesRepo {
    suspend fun fetchNowPlayingMovies(): MoviesListResponse
    suspend fun fetchPopularMovies(): MoviesListResponse
    suspend fun fetchTopRatedMovies(): MoviesListResponse
}