package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NowPlayingViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<NowPlayingUiState>(NowPlayingUiState.Uninitialized)
    val uiState: StateFlow<NowPlayingUiState> = _uiState.asStateFlow()

    fun fetchNowPlayingMovies() {
        _uiState.value = NowPlayingUiState.Loading
        viewModelScope.launch {
            try {
                val response = getNowPlayingMoviesUseCase.perform()
                _uiState.value = NowPlayingUiState.Success(moviesListResponse = response)
            } catch (e: Exception) {
                _uiState.value = NowPlayingUiState.Error(exceptionMessage = e.message.orEmpty())
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}