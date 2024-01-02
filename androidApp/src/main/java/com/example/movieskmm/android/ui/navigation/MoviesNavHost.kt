package com.example.movieskmm.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieskmm.android.ui.navigation.destination.MOVIE_DETAILS_PARAMS
import com.example.movieskmm.android.ui.navigation.destination.MOVIE_ID_ARGUMENT
import com.example.movieskmm.android.ui.navigation.destination.MovieDetailsDestination
import com.example.movieskmm.android.ui.navigation.destination.NowPlayingDestination
import com.example.movieskmm.android.ui.navigation.destination.PdfViewerDestination
import com.example.movieskmm.android.ui.screens.moviedetails.MovieDetailsScreen
import com.example.movieskmm.android.ui.screens.nowplaying.nowPlayingGraph
import com.example.movieskmm.android.ui.screens.pdfviewer.PdfViewerScreen
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
            onMovieSelected = { movieId ->
                navHostController.navigateToMovieDetails(movieId)
            },
            navController = navHostController,
            modifier = Modifier
        )
        topRatedGraph(
            onMovieSelected = { movieId ->
                navHostController.navigateToMovieDetails(movieId)
            },
            navController = navHostController,
            modifier = Modifier
        )
        popularGraph(
            onMovieSelected = { movieId ->
                navHostController.navigateToMovieDetails(movieId)
            },
            navController = navHostController,
            modifier = Modifier
        )
        composable(
            route = MovieDetailsDestination.route + MOVIE_DETAILS_PARAMS,
            arguments = listOf(
                navArgument(MOVIE_ID_ARGUMENT) {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(MOVIE_ID_ARGUMENT) ?: 0
            MovieDetailsScreen(
                movieId = movieId,
                modifier = modifier,
                upPress = { navHostController.navigateUp() },
                navigateToPdfViewer = {
                    navHostController.navigate(PdfViewerDestination.route)
                }
            )
        }
        composable(route = PdfViewerDestination.route) {
            PdfViewerScreen(
                upPress = { navHostController.navigateUp() }
            )
        }
    }
}

fun NavHostController.navigateToMovieDetails(movieId: Int) {
    navigate(MovieDetailsDestination.route + "/$movieId")
}