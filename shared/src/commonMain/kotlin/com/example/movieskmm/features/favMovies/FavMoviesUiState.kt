package com.example.movieskmm.features.favMovies

import com.example.movieskmm.domain.model.MovieItem

sealed class FavMoviesUiState {
    data class Success(val moviesList: List<MovieItem>) : FavMoviesUiState()
    data class Error(val exceptionMessage: String?) : FavMoviesUiState()
    data object Loading : FavMoviesUiState()
    data object Uninitialized : FavMoviesUiState()
}