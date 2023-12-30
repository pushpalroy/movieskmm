package com.example.movieskmm.features.topRatedMovies

import com.example.movieskmm.domain.usecase.GetTopRatedMoviesUseCase
import com.example.movieskmm.domain.util.NetworkResponse
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopRatedViewModel(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    /**
     * We need to make changes - from the side of Swift MutableStateFlow, StateFlow,
     * Flow will lose their generic type since they are interfaces. MOKO MVVM provides
     * special CMutableStateFlow, CStateFlow and CFlow classes to store the generic type in iOS.
     */
    private val _uiState = MutableStateFlow<TopRatedUiState>(
        TopRatedUiState.Uninitialized
    ).cMutableStateFlow()
    val uiState: CStateFlow<TopRatedUiState> = _uiState.asStateFlow().cStateFlow()

    fun fetchTopRatedMovies() {
        _uiState.value = TopRatedUiState.Loading
        viewModelScope.launch {
            try {
                when (val response = getTopRatedMoviesUseCase.perform()) {
                    is NetworkResponse.Success -> {
                        _uiState.value = TopRatedUiState.Success(
                            moviesList = response.data
                        )
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
        viewModelScope.cancel()
        super.onCleared()
    }
}