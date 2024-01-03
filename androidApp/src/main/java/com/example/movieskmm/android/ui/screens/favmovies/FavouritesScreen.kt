package com.example.movieskmm.android.ui.screens.favmovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieskmm.android.ui.components.MovieList
import com.example.movieskmm.domain.model.MovieItem
import com.example.movieskmm.features.favMovies.FavMoviesUiState
import com.example.movieskmm.features.favMovies.FavMoviesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    onMovieSelected: (Int) -> Unit
) {
    val viewModel = getViewModel<FavMoviesViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllFavouriteMovies()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is FavMoviesUiState.Loading -> {
                CircularProgressIndicator()
            }

            is FavMoviesUiState.Success -> {
                val moviesData = (uiState as FavMoviesUiState.Success)
                FavMoviesList(
                    moviesList = moviesData.moviesList,
                    onMovieSelected = onMovieSelected
                )
            }

            is FavMoviesUiState.Error -> {
                val errorMessage = (uiState as FavMoviesUiState.Error).exceptionMessage ?: ""
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun FavMoviesList(
    modifier: Modifier = Modifier,
    moviesList: List<MovieItem>,
    onMovieSelected: (Int) -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieList(
            modifier = Modifier,
            listItems = moviesList,
            onClick = onMovieSelected
        )
    }
}
