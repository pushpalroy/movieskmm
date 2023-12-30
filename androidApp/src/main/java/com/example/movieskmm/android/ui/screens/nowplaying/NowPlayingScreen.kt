package com.example.movieskmm.android.ui.screens.nowplaying

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
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingActions
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingUiState
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import dev.icerock.moko.mvvm.flow.compose.observeAsActions
import org.koin.androidx.compose.getViewModel

@Composable
fun NowPlayingScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: NowPlayingViewModel = getViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.actions.observeAsActions { action ->
        when (action) {
            NowPlayingActions.MoviesFetchSuccess -> {
                // Do something
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchNowPlayingMovies()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (uiState) {
            is NowPlayingUiState.Loading -> {
                CircularProgressIndicator()
            }

            is NowPlayingUiState.Success -> {
                val moviesData = (uiState as NowPlayingUiState.Success)
                NowPlayingMoviesList(
                    moviesList = moviesData.moviesList
                )
            }

            is NowPlayingUiState.Error -> {
                val errorMessage = (uiState as NowPlayingUiState.Error).exceptionMessage ?: ""
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun NowPlayingMoviesList(
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
