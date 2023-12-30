package com.example.movieskmm.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.movieskmm.android.ui.designsystem.MoviesAppTheme
import com.example.movieskmm.android.ui.designsystem.contentColorFor
import com.example.movieskmm.android.ui.navigation.destination.TopLevelDestination
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MoviesBottomBar(
    destinations: ImmutableList<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    MoviesNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            MoviesNavigationBarItem(
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }

                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = stringResource(
                            destination.iconTextId
                        )
                    )
                },
                label = {
                    Text(text = stringResource(id = destination.titleTextId))
                },
                alwaysShowLabel = true
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(
    destination: TopLevelDestination
) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false

/**
 * MoviesNavigationBar - Composable which will house the [MoviesNavigationBarItem]
 *
 * @param modifier
 * @param content
 */
@Composable
fun MoviesNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        BottomNavigation(
            modifier = modifier,
            content = content,
            backgroundColor = MoviesAppTheme.colors.uiBackgroundPrimary,
            contentColor = contentColorFor(MoviesAppTheme.colors.uiBackgroundPrimary)
        )
        MoviesDivider(thickness = 0.3.dp)
    }
}

/**
 * MoviesNavigationBarItem - Composable which will be used inside [RowScope] to create UI for [MoviesNavigationBar]
 *
 * @param selected to show if the item is currently selected or not
 * @param onClick block to be executed when the item is clicked
 * @param icon Image to be shown to user as identification when the item is not selected
 * @param modifier
 * @param selectedIcon Image to be shown to user as identification when the item is selected
 * @param enabled this should allow us to enable or disable the bottom navigation item
 * @param label composable to be shown under the icon
 * @param alwaysShowLabel Configure if the label should be visible or not
 */
@Composable
fun RowScope.MoviesNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        selectedContentColor = MoviesAppTheme.colors.uiBackgroundSecondary,
        unselectedContentColor = MoviesAppTheme.colors.textTertiary
    )
}

/**
 * A divider is a thin line that groups content in lists and layouts.
 *
 * @param modifier Optional [Modifier] for the entire component.
 * @param color color of the divider line
 * @param thickness thickness of the divider line, 1 dp is used by default. Using [Dp.Hairline]
 * will produce a single pixel divider regardless of screen density.
 * @param startIndent start offset of this line, no offset by default
 */
@Composable
@NonRestartableComposable
fun MoviesDivider(
    modifier: Modifier = Modifier,
    color: Color = MoviesAppTheme.colors.dividerPrimary,
    thickness: Dp = MoviesDividerDefaults.dividerThickness,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}

/** Contains the default values used by [MoviesDivider]. */
object MoviesDividerDefaults {

    /** The default thickness of divider*/
    val dividerThickness = 1.dp
}
