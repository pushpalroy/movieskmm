package com.example.movieskmm.features.topRatedMovies

import com.example.movieskmm.domain.usecase.GetTopRatedMoviesUseCase
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

open class TopRatedViewModel : KMMViewModel(), KoinComponent {

    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase by inject()

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
            when (val response = getTopRatedMoviesUseCase.perform()) {
                is NetworkResponse.Success -> {
                    _uiState.value = TopRatedUiState.Success(moviesList = response.data)
                }

                is NetworkResponse.Failure -> {
                    _uiState.value = TopRatedUiState.Error(
                        exceptionMessage = response.throwable.message
                    )
                }

                is NetworkResponse.Unauthorized -> {
                    _uiState.value = TopRatedUiState.Error(
                        exceptionMessage = response.throwable.message
                    )
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}