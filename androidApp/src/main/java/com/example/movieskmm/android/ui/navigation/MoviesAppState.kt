package com.example.movieskmm.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieskmm.android.ui.navigation.destination.FavouritesDestination
import com.example.movieskmm.android.ui.navigation.destination.MoviesNavDestination
import com.example.movieskmm.android.ui.navigation.destination.NowPlayingDestination
import com.example.movieskmm.android.ui.navigation.destination.PopularDestination
import com.example.movieskmm.android.ui.navigation.destination.TopLevelDestination
import com.example.movieskmm.android.ui.navigation.destination.TopRatedDestination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun rememberMoviesAppState(
    navController: NavHostController = rememberNavController()
): MoviesAppState {
    return remember(navController) {
        MoviesAppState(navController)
    }
}

@Stable
class MoviesAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            NowPlayingDestination.route -> NowPlayingDestination
            TopRatedDestination.route -> TopRatedDestination
            PopularDestination.route -> PopularDestination
            FavouritesDestination.route -> FavouritesDestination
            else -> null
        }

    val topLevelDestination: ImmutableList<TopLevelDestination> = persistentListOf(
        NowPlayingDestination,
        TopRatedDestination,
        PopularDestination,
        FavouritesDestination
    )

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * Top level destinations have only one copy of the destination of the back stack, and save and
     * restore state whenever you navigate to and from it.
     * Regular destinations can have multiple copies in the back stack and state isn't saved nor
     * restored.
     *
     * @param destination: The [MoviesNavDestination] the app needs to navigate to.
     */
    fun navigate(
        destination: MoviesNavDestination
    ) {
        if (destination is TopLevelDestination) {
            navController.navigate(destination.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re-selecting the same item
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                restoreState = true
            }
        } else {
            navController.navigate(destination.route)
        }
    }
}
