package com.example.movieskmm.features.topRatedMovies

import com.example.movieskmm.domain.model.MoviesList

sealed class TopRatedUiState {
    data class Success(val moviesList: MoviesList) : TopRatedUiState()
    data class Error(val exceptionMessage: String?) : TopRatedUiState()
    data object Loading : TopRatedUiState()
    data object Uninitialized : TopRatedUiState()
}