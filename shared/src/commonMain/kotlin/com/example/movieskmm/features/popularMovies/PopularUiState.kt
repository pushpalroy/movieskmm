package com.example.movieskmm.features.popularMovies

import com.example.movieskmm.domain.model.MoviesList

sealed class PopularUiState {
    data class Success(val moviesList: MoviesList) : PopularUiState()
    data class Error(val exceptionMessage: String?) : PopularUiState()
    data object Loading : PopularUiState()
    data object Uninitialized : PopularUiState()
}