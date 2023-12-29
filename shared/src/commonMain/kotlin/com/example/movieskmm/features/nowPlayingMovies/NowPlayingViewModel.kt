package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.util.NetworkResponse
import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NowPlayingViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    /**
     * We need to make changes - from the side of Swift MutableStateFlow, StateFlow,
     * Flow will lose their generic type since they are interfaces. MOKO MVVM provides
     * special CMutableStateFlow, CStateFlow and CFlow classes to store the generic type in iOS.
     */
    private val _uiState = MutableStateFlow<NowPlayingUiState>(
        NowPlayingUiState.Uninitialized
    ).cMutableStateFlow()
    val uiState: CStateFlow<NowPlayingUiState> = _uiState.asStateFlow().cStateFlow()

    private val _actions = Channel<NowPlayingActions>()
    val actions: CFlow<NowPlayingActions> get() = _actions.receiveAsFlow().cFlow()

    fun fetchNowPlayingMovies() {
        _uiState.value = NowPlayingUiState.Loading
        viewModelScope.launch {
            try {
                when (val response = getNowPlayingMoviesUseCase.perform()) {
                    is NetworkResponse.Success -> {
                        _uiState.value = NowPlayingUiState.Success(
                            moviesListResponse = response.data
                        )
                        _actions.send(NowPlayingActions.MoviesFetchSuccess)
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