package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.util.NetworkResponse
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
                when (val response = getNowPlayingMoviesUseCase.perform()) {
                    is NetworkResponse.Success -> {
                        _uiState.value =
                            NowPlayingUiState.Success(moviesListResponse = response.data)
                    }

                    is NetworkResponse.Failure -> {
                        _uiState.value =
                            NowPlayingUiState.Error(exceptionMessage = response.throwable.message)
                    }

                    is NetworkResponse.Unauthorized -> {
                        _uiState.value =
                            NowPlayingUiState.Error(exceptionMessage = response.throwable.message)
                    }
                }
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