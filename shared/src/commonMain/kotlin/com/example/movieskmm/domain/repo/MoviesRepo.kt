package com.example.movieskmm.domain.repo

import com.example.movieskmm.domain.model.MoviesListResponse
import com.example.movieskmm.domain.util.NetworkResponse

interface MoviesRepo {
    suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesListResponse>
    suspend fun fetchPopularMovies(): NetworkResponse<MoviesListResponse>
    suspend fun fetchTopRatedMovies(): NetworkResponse<MoviesListResponse>
}