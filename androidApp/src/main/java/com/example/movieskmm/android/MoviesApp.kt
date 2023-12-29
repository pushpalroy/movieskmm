package com.example.movieskmm.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.movieskmm.android.theme.MoviesAppTheme
import com.example.movieskmm.android.ui.home.HomeSections
import com.example.movieskmm.android.ui.home.addHomeGraph
import com.example.movieskmm.android.ui.navigation.MainDestinations
import com.example.movieskmm.android.ui.navigation.rememberMoviesNavController

@Composable
fun MoviesApp() {
    MoviesAppTheme {
        val moviesNavController = rememberMoviesNavController()
        NavHost(
            navController = moviesNavController.navController,
            startDestination = MainDestinations.HOME_ROUTE
        ) {
            moviesNavGraph(
                onMovieSelected = moviesNavController::navigateToMovieDetail,
                upPress = moviesNavController::upPress,
                onNavigateToRoute = moviesNavController::navigateToBottomBarRoute
            )
        }
    }
}

private fun NavGraphBuilder.moviesNavGraph(
    onMovieSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.NOW_PLAYING.route
    ) {
        addHomeGraph(onMovieSelected, onNavigateToRoute)
    }
//    composable(
//        "${MainDestinations.MOVIE_DETAIL_ROUTE}/{${MainDestinations.MOVIE_ID_KEY}}",
//        arguments = listOf(navArgument(MainDestinations.MOVIE_ID_KEY) { type = NavType.LongType })
//    ) { backStackEntry ->
//        val arguments = requireNotNull(backStackEntry.arguments)
//        val snackId = arguments.getLong(MainDestinations.MOVIE_ID_KEY)
//        MovieDetail(snackId, upPress)
//    }
}