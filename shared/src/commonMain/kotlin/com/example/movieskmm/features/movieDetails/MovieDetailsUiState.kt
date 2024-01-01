package com.example.movieskmm.features.movieDetails

import com.example.movieskmm.domain.model.MovieDetails

sealed class MovieDetailsUiState {
    data class Success(val movieDetails: MovieDetails) : MovieDetailsUiState()
    data class Error(val exceptionMessage: String?) : MovieDetailsUiState()
    data object Loading : MovieDetailsUiState()
}