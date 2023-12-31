package com.example.movieskmm.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieskmm.android.ui.navigation.destination.NowPlayingDestination
import com.example.movieskmm.android.ui.screens.nowplaying.nowPlayingGraph
import com.example.movieskmm.android.ui.screens.popular.popularGraph
import com.example.movieskmm.android.ui.screens.toprated.topRatedGraph

@Composable
fun MoviesNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NowPlayingDestination.route,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        nowPlayingGraph(
            onMovieSelected = {},
            navController = navHostController,
            modifier = Modifier
        )
        topRatedGraph(
            onMovieSelected = {},
            navController = navHostController,
            modifier = Modifier
        )
        popularGraph(
            onMovieSelected = {},
            navController = navHostController,
            modifier = Modifier
        )
    }
}