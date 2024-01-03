package com.example.movieskmm.features.favMovies

import com.example.movieskmm.domain.usecase.GetAllFavMoviesUseCase
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

open class FavMoviesViewModel : KMMViewModel(), KoinComponent {

    private val getAllFavMoviesUseCase: GetAllFavMoviesUseCase by inject()
    private val _uiState = MutableStateFlow<FavMoviesUiState>(FavMoviesUiState.Uninitialized)

    @NativeCoroutinesState
    val uiState: StateFlow<FavMoviesUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FavMoviesUiState.Uninitialized
    )

    fun getAllFavouriteMovies() {
        _uiState.value = FavMoviesUiState.Loading
        viewModelScope.coroutineScope.launch {
            getAllFavMoviesUseCase.perform().collect { listOfFavMovies ->
                _uiState.value = FavMoviesUiState.Success(moviesList = listOfFavMovies)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}