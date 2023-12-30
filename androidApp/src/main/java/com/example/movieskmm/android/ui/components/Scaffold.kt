package com.example.movieskmm.android.ui.components

import android.content.res.Resources
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.example.movieskmm.android.model.SnackBarManager
import com.example.movieskmm.android.ui.designsystem.MoviesAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Wrap Material [androidx.compose.material.Scaffold] and set [MoviesAppTheme] colors.
 */
@Composable
fun MoviesScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MoviesAppTheme.colors.uiBackgroundPrimary,
    drawerContentColor: Color = MoviesAppTheme.colors.textSecondary,
    drawerScrimColor: Color = MoviesAppTheme.colors.uiBorder,
    backgroundColor: Color = MoviesAppTheme.colors.uiBackgroundPrimary,
    contentColor: Color = MoviesAppTheme.colors.textSecondary,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackBarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        drawerContent = drawerContent,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}

/**
 * Remember and creates an instance of [MoviesScaffoldState]
 */
@Composable
fun rememberMoviesScaffoldState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MoviesScaffoldState = remember(scaffoldState, snackBarManager, resources, coroutineScope) {
    MoviesScaffoldState(scaffoldState, snackBarManager, resources, coroutineScope)
}

/**
 * Responsible for holding [ScaffoldState], handles the logic of showing snackBar messages
 */
@Stable
class MoviesScaffoldState(
    val scaffoldState: ScaffoldState,
    private val snackBarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process snackBars coming from SnackbarManager
    init {
        coroutineScope.launch {
            snackBarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)
                    // Notify the SnackBarManager so it can remove the current message from the list
                    snackBarManager.setMessageShown(message.id)
                    // Display the snackBar on the screen. `showSnackBar` is a function
                    // that suspends until the snackBar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text.toString())
                }
            }
        }
    }
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
