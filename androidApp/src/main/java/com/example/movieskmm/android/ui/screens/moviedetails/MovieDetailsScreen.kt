package com.example.movieskmm.android.ui.screens.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieskmm.android.ui.components.mirroringBackIcon
import com.example.movieskmm.android.ui.designsystem.MoviesAppTheme
import com.example.movieskmm.data.util.ApiConstants
import com.example.movieskmm.domain.model.asMovieItem
import com.example.movieskmm.features.movieDetails.MovieDetailsUiState
import com.example.movieskmm.features.movieDetails.MovieDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    navigateToPdfViewer: () -> Unit,
    upPress: () -> Unit,
) {
    val viewModel = getViewModel<MovieDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetails(movieId = movieId)
    }

    when (uiState) {
        is MovieDetailsUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
        }

        is MovieDetailsUiState.Success -> {
            val moviesDetailsData = (uiState as MovieDetailsUiState.Success)
            val movie = moviesDetailsData.movieDetails
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Box(
                    Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(ApiConstants.IMAGE_BASE_URL.plus(movie.backdropPath))
                            .crossfade(300)
                            .build(),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                    IconButton(
                        onClick = upPress,
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .size(36.dp)
                            .background(
                                color = MoviesAppTheme.colors.uiBackgroundTertiary.copy(alpha = 0.32f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = mirroringBackIcon(),
                            tint = MoviesAppTheme.colors.iconPrimaryActive,
                            contentDescription = "Back button"
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = movie.title,
                    style = MoviesAppTheme.typography.display4,
                    color = MoviesAppTheme.colors.textPrimary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = movie.tagline,
                    style = MoviesAppTheme.typography.display1,
                    color = MoviesAppTheme.colors.textTertiary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Vote",
                            style = MoviesAppTheme.typography.display1,
                            color = MoviesAppTheme.colors.textInteractive_1,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(
                            text = movie.voteAverage.toString(),
                            style = MoviesAppTheme.typography.body1,
                            color = MoviesAppTheme.colors.textPrimary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Popularity",
                            style = MoviesAppTheme.typography.display1,
                            color = MoviesAppTheme.colors.textInteractive_1,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(
                            text = movie.popularity.toString(),
                            style = MoviesAppTheme.typography.body1,
                            color = MoviesAppTheme.colors.textPrimary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Budget",
                            style = MoviesAppTheme.typography.display1,
                            color = MoviesAppTheme.colors.textInteractive_1,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(
                            text = movie.budget.toString(),
                            style = MoviesAppTheme.typography.body1,
                            color = MoviesAppTheme.colors.textPrimary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    IconButton(
                        onClick = navigateToPdfViewer,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(36.dp)
                            .background(
                                color = MoviesAppTheme.colors.uiBackgroundTertiary.copy(alpha = 0.20f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            tint = MoviesAppTheme.colors.iconPrimaryActive,
                            contentDescription = "Download"
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.addMovieToFav(movie.asMovieItem())
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            tint = MoviesAppTheme.colors.iconPrimaryActive,
                            contentDescription = "Add to favorite"
                        )
                    }
                }
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "Overview",
                    style = MoviesAppTheme.typography.display1,
                    color = MoviesAppTheme.colors.textInteractive_1,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = movie.overview,
                    style = MoviesAppTheme.typography.body1,
                    color = MoviesAppTheme.colors.textPrimary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        is MovieDetailsUiState.Error -> {
            val errorMessage = (uiState as MovieDetailsUiState.Error).exceptionMessage ?: ""
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
        }

        else -> Unit
    }
}