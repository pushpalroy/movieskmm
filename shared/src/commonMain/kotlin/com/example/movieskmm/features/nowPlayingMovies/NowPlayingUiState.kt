package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.model.MoviesList

sealed interface NowPlayingUiState {
    data class Success(val moviesList: MoviesList) : NowPlayingUiState
    data class Error(val exceptionMessage: String?) : NowPlayingUiState
    data object Loading : NowPlayingUiState
    data object Uninitialized : NowPlayingUiState
}

sealed interface NowPlayingActions {
    data object MoviesFetchSuccess : NowPlayingActions
}