package com.example.movieskmm.android.ui.screens.toprated

import androidx.compose.foundation.layout.Arrangement
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
import com.example.movieskmm.domain.model.MoviesList
import com.example.movieskmm.features.topRatedMovies.TopRatedUiState
import com.example.movieskmm.features.topRatedMovies.TopRatedViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TopRatedScreen(
    onMovieSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = getViewModel<TopRatedViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchTopRatedMovies()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (uiState) {
            is TopRatedUiState.Loading -> {
                CircularProgressIndicator()
            }

            is TopRatedUiState.Success -> {
                val moviesData = (uiState as TopRatedUiState.Success)
                TopRatedMoviesList(
                    moviesList = moviesData.moviesList,
                    onMovieSelected = onMovieSelected
                )
            }

            is TopRatedUiState.Error -> {
                val errorMessage = (uiState as TopRatedUiState.Error).exceptionMessage ?: ""
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun TopRatedMoviesList(
    modifier: Modifier = Modifier,
    moviesList: MoviesList,
    onMovieSelected: (Int) -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieList(
            modifier = Modifier,
            listItems = moviesList.results,
            onClick = onMovieSelected
        )
    }
}
