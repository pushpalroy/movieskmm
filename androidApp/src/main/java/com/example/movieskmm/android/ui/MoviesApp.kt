package com.example.movieskmm.android.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.movieskmm.android.ui.designsystem.MoviesAppTheme
import com.example.movieskmm.android.ui.components.MoviesBottomBar
import com.example.movieskmm.android.ui.components.MoviesScaffold
import com.example.movieskmm.android.ui.components.MoviesSurface
import com.example.movieskmm.android.ui.navigation.MoviesNavHost
import com.example.movieskmm.android.ui.navigation.rememberMoviesAppState

@Composable
fun MoviesApp(
    modifier: Modifier = Modifier
) {
    MoviesAppTheme {
        val appState = rememberMoviesAppState()
        val navHostController = appState.navController
        val destination = remember { appState.topLevelDestination }

        MoviesScaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = appState.currentTopLevelDestination != null,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    MoviesBottomBar(
                        destinations = destination,
                        onNavigateToDestination = appState::navigate,
                        currentDestination = appState.currentDestination,
                    )
                }
            },
            modifier = modifier
        ) { paddingValues ->
            MoviesSurface(
                modifier = modifier.padding(paddingValues)
            ) {
                MoviesNavHost(
                    navHostController = navHostController
                )
            }
        }
    }
}