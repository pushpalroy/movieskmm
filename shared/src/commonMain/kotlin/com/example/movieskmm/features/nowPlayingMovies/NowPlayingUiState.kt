package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.movie.model.MoviesListDomain

sealed interface NowPlayingUiState {
    data class Success(val moviesListDomain: MoviesListDomain) : NowPlayingUiState
    data class Error(val exceptionMessage: String) : NowPlayingUiState
    data object Loading : NowPlayingUiState
    data object Uninitialized : NowPlayingUiState
}
