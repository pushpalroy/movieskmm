package com.example.movieskmm.android.ui.screens.toprated

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieskmm.android.ui.navigation.destination.TopRatedDestination

fun NavGraphBuilder.topRatedGraph(
    onMovieSelected: (Int) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(TopRatedDestination.route) {
        TopRatedScreen(onMovieSelected = onMovieSelected, modifier = modifier)
    }
}