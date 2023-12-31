package com.example.movieskmm.features.topRatedMovies

import com.example.movieskmm.domain.usecase.GetTopRatedMoviesUseCase
import com.example.movieskmm.domain.util.NetworkResponse
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingUiState
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class TopRatedViewModel : KMMViewModel(), KoinComponent {

    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase by inject()

    /**
     * We need to make changes - from the side of Swift MutableStateFlow, StateFlow,
     * Flow will lose their generic type since they are interfaces. MOKO MVVM provides
     * special CMutableStateFlow, CStateFlow and CFlow classes to store the generic type in iOS.
     */
    private val _uiState = MutableStateFlow<TopRatedUiState>(TopRatedUiState.Uninitialized)


    @NativeCoroutinesState
    val uiState: StateFlow<TopRatedUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        TopRatedUiState.Uninitialized
    )

    fun fetchTopRatedMovies() {
        _uiState.value = TopRatedUiState.Loading
        viewModelScope.coroutineScope.launch {
            try {
                when (val response = getTopRatedMoviesUseCase.perform()) {
                    is NetworkResponse.Success -> {
                        _uiState.value = TopRatedUiState.Success(moviesList = response.data)
                    }

                    is NetworkResponse.Failure -> {
                        _uiState.value =
                            TopRatedUiState.Error(exceptionMessage = response.throwable.message)
                    }

                    is NetworkResponse.Unauthorized -> {
                        _uiState.value =
                            TopRatedUiState.Error(exceptionMessage = response.throwable.message)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = TopRatedUiState.Error(exceptionMessage = e.message.orEmpty())
            }
        }
    }

    override fun onCleared() {
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}