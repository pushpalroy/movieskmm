package com.example.movieskmm.android.ui.screens.popular

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
import com.example.movieskmm.features.popularMovies.PopularUiState
import com.example.movieskmm.features.popularMovies.PopularViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PopularScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = getViewModel<PopularViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchPopularMovies()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (uiState) {
            is PopularUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PopularUiState.Success -> {
                val moviesData = (uiState as PopularUiState.Success)
                PopularMoviesList(
                    moviesList = moviesData.moviesList
                )
            }

            is PopularUiState.Error -> {
                val errorMessage = (uiState as PopularUiState.Error).exceptionMessage ?: ""
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun PopularMoviesList(
    modifier: Modifier = Modifier,
    moviesList: MoviesList
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieList(
            modifier = Modifier,
            listItems = moviesList.results,
            onclick = {})
    }
}
