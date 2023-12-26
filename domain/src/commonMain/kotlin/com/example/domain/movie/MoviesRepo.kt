package com.example.domain.movie

import com.example.domain.movie.model.MoviesListDomain

interface MoviesRepo {
    suspend fun fetchNowPlayingMovies(): MoviesListDomain
    suspend fun fetchPopularMovies(): MoviesListDomain
    suspend fun fetchTopRatedMovies(): MoviesListDomain
}