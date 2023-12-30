package com.example.movieskmm.android.ui.toprated

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieskmm.android.ui.navigation.destination.TopRatedDestination

fun NavGraphBuilder.topRatedGraph(
    onMovieSelected: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(TopRatedDestination.route) {
        TopRatedScreen(modifier)
    }
}