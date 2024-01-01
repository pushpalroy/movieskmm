package com.example.movieskmm.android.ui.screens.nowplaying

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieskmm.android.ui.navigation.destination.NowPlayingDestination

fun NavGraphBuilder.nowPlayingGraph(
    onMovieSelected: (Int) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(NowPlayingDestination.route) {
        NowPlayingScreen(onMovieSelected = onMovieSelected, modifier = modifier)
    }
}