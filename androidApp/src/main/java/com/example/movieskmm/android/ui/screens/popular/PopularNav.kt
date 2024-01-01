package com.example.movieskmm.android.ui.screens.popular

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieskmm.android.ui.navigation.destination.PopularDestination

fun NavGraphBuilder.popularGraph(
    onMovieSelected: (Int) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(PopularDestination.route) {
        PopularScreen(onMovieSelected = onMovieSelected, modifier = modifier)
    }
}