package com.example.movieskmm.android.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieskmm.android.theme.MoviesAppTheme
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingActions
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingUiState
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import dev.icerock.moko.mvvm.flow.compose.observeAsActions
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    MoviesAppTheme {
        val context: Context = LocalContext.current
        val viewModel: NowPlayingViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        viewModel.actions.observeAsActions { action ->
            when (action) {
                NowPlayingActions.MoviesFetchSuccess ->
                    Toast.makeText(context, "Fetching movies success!", Toast.LENGTH_SHORT).show()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (uiState) {
                is NowPlayingUiState.Uninitialized -> {
                    Button(onClick = {
                        viewModel.fetchNowPlayingMovies()
                    }) {
                        Text("Fetch Now Playing Movies")
                    }
                }

                is NowPlayingUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is NowPlayingUiState.Success -> {
                    val pdfData = (uiState as NowPlayingUiState.Success)
                    Text(
                        text = pdfData.moviesListResponse.results.first().title,
                        color = Color.Green
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
}
