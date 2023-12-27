package com.example.movieskmm.domain.movie

import com.example.movieskmm.domain.movie.model.MoviesListDomain

interface MoviesRepo {
    suspend fun fetchNowPlayingMovies(): MoviesListDomain
    suspend fun fetchPopularMovies(): MoviesListDomain
    suspend fun fetchTopRatedMovies(): MoviesListDomain
}