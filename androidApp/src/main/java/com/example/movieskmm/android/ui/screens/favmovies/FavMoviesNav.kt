package com.example.movieskmm.android.ui.screens.favmovies

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieskmm.android.ui.navigation.destination.FavouritesDestination

fun NavGraphBuilder.favouriteGraph(
    onMovieSelected: (Int) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(FavouritesDestination.route) {
        FavouritesScreen(onMovieSelected = onMovieSelected, modifier = modifier)
    }
}