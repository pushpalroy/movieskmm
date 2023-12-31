package com.example.movieskmm.features.nowPlayingMovies

import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.util.NetworkResponse
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class NowPlayingViewModel : KMMViewModel(), KoinComponent {

    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase by inject()

    /**
     * We need to make changes - from the side of Swift MutableStateFlow, StateFlow,
     * Flow will lose their generic type since they are interfaces. MOKO MVVM provides
     * special CMutableStateFlow, CStateFlow and CFlow classes to store the generic type in iOS.
     */
    private val _uiState = MutableStateFlow<NowPlayingUiState>(NowPlayingUiState.Uninitialized)

    @NativeCoroutinesState
    val uiState: StateFlow<NowPlayingUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        NowPlayingUiState.Uninitialized
    )

    fun fetchNowPlayingMovies() {
        _uiState.value = NowPlayingUiState.Loading
        viewModelScope.coroutineScope.launch {
            try {
                when (val response = getNowPlayingMoviesUseCase.perform()) {
                    is NetworkResponse.Success -> {
                        _uiState.value = NowPlayingUiState.Success(moviesList = response.data)
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
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}