package com.example.movieskmm.android.ui

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieskmm.android.theme.MoviesAppTheme
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingUiState
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    MoviesAppTheme {
        val viewModel: NowPlayingViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                        Text("Download PDF")
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
                    val errorMessage = (uiState as NowPlayingUiState.Error).exceptionMessage
                    if (errorMessage.isNotEmpty()) {
                        Text(text = errorMessage, color = Color.Red)
                    }
                }

                else -> Unit
            }
        }
    }
}
