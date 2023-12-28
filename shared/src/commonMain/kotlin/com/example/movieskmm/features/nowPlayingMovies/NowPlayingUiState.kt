package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.model.MoviesListResponse

sealed interface NowPlayingUiState {
    data class Success(val moviesListResponse: MoviesListResponse) : NowPlayingUiState
    data class Error(val exceptionMessage: String?) : NowPlayingUiState
    data object Loading : NowPlayingUiState
    data object Uninitialized : NowPlayingUiState
}
