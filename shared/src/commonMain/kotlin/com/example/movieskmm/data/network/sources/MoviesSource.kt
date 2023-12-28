package com.example.movieskmm.data.network.sources

import com.example.movieskmm.data.network.entity.MoviesListEntity

interface MoviesSource {
    suspend fun fetchNowPlayingMovies(): MoviesListEntity
    suspend fun fetchPopularMovies(): MoviesListEntity
    suspend fun fetchTopRatedMovies(): MoviesListEntity
}