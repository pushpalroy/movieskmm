package com.example.movieskmm.features.movieDetails

import com.example.movieskmm.domain.usecase.GetMovieDetailsByIdUseCase
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

open class MovieDetailsViewModel : KMMViewModel(), KoinComponent {

    private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase by inject()

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)

    @NativeCoroutinesState
    val uiState: StateFlow<MovieDetailsUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        MovieDetailsUiState.Loading
    )

    fun fetchMovieDetails(movieId: Int) {
        _uiState.value = MovieDetailsUiState.Loading
        viewModelScope.coroutineScope.launch {
            when (val response = getMovieDetailsByIdUseCase.perform(params = movieId)) {
                is NetworkResponse.Success -> {
                    _uiState.value = MovieDetailsUiState.Success(movieDetails = response.data)
                }

                is NetworkResponse.Failure -> {
                    _uiState.value = MovieDetailsUiState.Error(
                        exceptionMessage = response.throwable.message
                    )
                }

                is NetworkResponse.Unauthorized -> {
                    _uiState.value =
                        MovieDetailsUiState.Error(exceptionMessage = response.throwable.message)
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}