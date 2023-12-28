package com.example.movieskmm.data.network.sources

import com.example.movieskmm.data.network.entity.MoviesListEntity
import com.example.movieskmm.domain.util.NetworkResponse

interface MoviesSource {
    suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesListEntity>
    suspend fun fetchPopularMovies(): NetworkResponse<MoviesListEntity>
    suspend fun fetchTopRatedMovies(): NetworkResponse<MoviesListEntity>
}