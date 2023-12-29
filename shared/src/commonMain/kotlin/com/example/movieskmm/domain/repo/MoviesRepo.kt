package com.example.movieskmm.domain.repo

import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.domain.util.NetworkResponse

interface MoviesRepo {
    suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesList>
    suspend fun fetchPopularMovies(): NetworkResponse<MoviesList>
    suspend fun fetchTopRatedMovies(): NetworkResponse<MoviesList>
}