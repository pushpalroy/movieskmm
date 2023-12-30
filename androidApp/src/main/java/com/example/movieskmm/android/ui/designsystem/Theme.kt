package com.example.movieskmm.android.ui.designsystem

import androidx.annotation.RequiresPermission
import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val MoviesColorPalette = MoviesColors(
    uiBackgroundPrimary = black,
    uiBackgroundSecondary = white,
    uiBackgroundTertiary = white.copy(alpha = 0.7f),
    uiBackgroundGradient_1 = listOf(color_mint, color_sky),
    uiBackgroundGradient_2 = listOf(color_peachfuzz, color_galaxy),
    uiBackgroundGradient_3 = listOf(color_capri, color_honeydew),
    uiBackgroundGradient_4 = listOf(color_strawberry, color_tangerine),
    uiBackgroundGradient_5 = listOf(color_bikini, color_frosting),
    uiControlPrimary = gray_55,
    uiControlSecondary = black,
    shadow = black,
    uiFloated = gray_33,
    uiBorder = black,
    textPrimary = white,
    textSecondary = black,
    textTertiary = gray_88,
    textInteractive_1 = color_capri,
    textInteractive_2 = error_red,
    iconPrimaryInactive = gray_bb,
    iconPrimaryActive = white,
    iconSecondary = success_green,
    dividerPrimary = gray_55,
    dividerSecondary = gray_bb,
    error = error_red,
    notificationBadge = white,
    warning = warning_yellow,
    dialogBackground = background_gray
)

@Composable
fun MoviesAppTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colors = MoviesColorPalette

    val colorPalette = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }
    colorPalette.update(colors)

    SideEffect {
        systemUiController.setSystemBarsColor(color = black)
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing,
        LocalColors provides colorPalette,
        LocalTypography provides Typography
    ) {
        MaterialTheme(
            typography = debugTypography(),
            shapes = Shapes,
            colors = debugColors(true),
            content = content
        )
    }
}

object MoviesAppTheme {
    val colors: MoviesColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val spacing: MoviesSpacing
        @Composable
        @RequiresPermission.Read
        get() = LocalSpacing.current

    val typography: MoviesTypography
        @Composable
        @RequiresPermission.Read
        get() = LocalTypography.current
}

@Stable
class MoviesColors(
    uiBackgroundPrimary: Color,
    uiBackgroundSecondary: Color,
    uiBackgroundTertiary: Color,
    uiBackgroundGradient_1: List<Color>,
    uiBackgroundGradient_2: List<Color>,
    uiBackgroundGradient_3: List<Color>,
    uiBackgroundGradient_4: List<Color>,
    uiBackgroundGradient_5: List<Color>,
    uiControlPrimary: Color,
    uiControlSecondary: Color,
    shadow: Color,
    uiFloated: Color,
    uiBorder: Color,
    textPrimary: Color,
    textSecondary: Color,
    textTertiary: Color,
    textInteractive_1: Color,
    textInteractive_2: Color,
    iconPrimaryInactive: Color,
    iconPrimaryActive: Color,
    iconSecondary: Color,
    dividerPrimary: Color,
    dividerSecondary: Color,
    error: Color,
    notificationBadge: Color,
    warning: Color,
    dialogBackground: Color
) {
    var uiBackgroundPrimary by mutableStateOf(uiBackgroundPrimary)
        private set
    var uiBackgroundSecondary by mutableStateOf(uiBackgroundSecondary)
        private set
    var uiBackgroundTertiary by mutableStateOf(uiBackgroundTertiary)
        private set
    var uiBackgroundGradient_1 by mutableStateOf(uiBackgroundGradient_1)
        private set
    var uiBackgroundGradient_2 by mutableStateOf(uiBackgroundGradient_2)
        private set
    var uiBackgroundGradient_3 by mutableStateOf(uiBackgroundGradient_3)
        private set
    var uiBackgroundGradient_4 by mutableStateOf(uiBackgroundGradient_4)
        private set
    var uiBackgroundGradient_5 by mutableStateOf(uiBackgroundGradient_5)
        private set
    var uiControlPrimary by mutableStateOf(uiControlPrimary)
        private set
    var uiControlSecondary by mutableStateOf(uiControlSecondary)
        private set
    var shadow by mutableStateOf(shadow)
        private set
    var uiFloated by mutableStateOf(uiFloated)
        private set
    var uiBorder by mutableStateOf(uiBorder)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textTertiary by mutableStateOf(textTertiary)
        private set
    var textInteractive_1 by mutableStateOf(textInteractive_1)
        private set
    var textInteractive_2 by mutableStateOf(textInteractive_2)
        private set
    var iconPrimaryInactive by mutableStateOf(iconPrimaryInactive)
        private set
    var iconPrimaryActive by mutableStateOf(iconPrimaryActive)
        private set
    var iconSecondary by mutableStateOf(iconSecondary)
        private set
    var dividerPrimary by mutableStateOf(dividerPrimary)
        private set
    var dividerSecondary by mutableStateOf(dividerSecondary)
        private set
    var error by mutableStateOf(error)
        private set
    var notificationBadge by mutableStateOf(notificationBadge)
        private set
    var warning by mutableStateOf(warning)
        private set
    var dialogBackground by mutableStateOf(dialogBackground)
        private set

    fun update(other: MoviesColors) {
        uiBackgroundPrimary = other.uiBackgroundPrimary
        uiBackgroundSecondary = other.uiBackgroundSecondary
        uiBackgroundTertiary = other.uiBackgroundTertiary
        uiBackgroundGradient_1 = other.uiBackgroundGradient_1
        uiBackgroundGradient_2 = other.uiBackgroundGradient_2
        uiBackgroundGradient_3 = other.uiBackgroundGradient_3
        uiBackgroundGradient_4 = other.uiBackgroundGradient_4
        uiBackgroundGradient_5 = other.uiBackgroundGradient_5
        uiControlPrimary = other.uiControlPrimary
        uiControlSecondary = other.uiControlSecondary
        shadow = other.shadow
        uiFloated = other.uiFloated
        uiBorder = other.uiBorder
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textTertiary = other.textTertiary
        textInteractive_1 = other.textInteractive_1
        textInteractive_2 = other.textInteractive_2
        iconPrimaryInactive = other.iconPrimaryInactive
        iconPrimaryActive = other.iconPrimaryActive
        iconSecondary = other.iconSecondary
        dividerPrimary = other.dividerPrimary
        dividerSecondary = other.dividerSecondary
        error = other.error
        notificationBadge = other.notificationBadge
        warning = other.warning
        dialogBackground = other.dialogBackground
    }

    fun copy(): MoviesColors = MoviesColors(
        uiBackgroundPrimary = uiBackgroundPrimary,
        uiBackgroundSecondary = uiBackgroundSecondary,
        uiBackgroundTertiary = uiBackgroundTertiary,
        uiBackgroundGradient_1 = uiBackgroundGradient_1,
        uiBackgroundGradient_2 = uiBackgroundGradient_2,
        uiBackgroundGradient_3 = uiBackgroundGradient_3,
        uiBackgroundGradient_4 = uiBackgroundGradient_4,
        uiBackgroundGradient_5 = uiBackgroundGradient_5,
        uiControlPrimary = uiControlPrimary,
        uiControlSecondary = uiControlSecondary,
        shadow = shadow,
        uiFloated = uiFloated,
        uiBorder = uiBorder,
        textPrimary = textPrimary,
        textSecondary = textSecondary,
        textTertiary = textTertiary,
        textInteractive_1 = textInteractive_1,
        textInteractive_2 = textInteractive_2,
        iconPrimaryInactive = iconPrimaryInactive,
        iconPrimaryActive = iconPrimaryActive,
        iconSecondary = iconSecondary,
        dividerPrimary = dividerPrimary,
        dividerSecondary = dividerSecondary,
        error = error,
        notificationBadge = notificationBadge,
        warning = warning,
        dialogBackground = dialogBackground
    )
}

val LocalColors = staticCompositionLocalOf<MoviesColors> {
    error("No MoviesColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [MoviesAppTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)

/**
 * A Material [Typography] implementation which sets all text's font size to 0.sp to discourage usage of
 * [MaterialTheme.typography] in preference to [MoviesAppTheme.typography].
 */
fun debugTypography() = Typography(
    h1 = TextStyle(fontSize = 0.sp),
    h2 = TextStyle(fontSize = 0.sp),
    h3 = TextStyle(fontSize = 0.sp),
    h4 = TextStyle(fontSize = 0.sp),
    h5 = TextStyle(fontSize = 0.sp),
    h6 = TextStyle(fontSize = 0.sp),
    subtitle1 = TextStyle(fontSize = 0.sp),
    subtitle2 = TextStyle(fontSize = 0.sp),
    body1 = TextStyle(fontSize = 0.sp),
    body2 = TextStyle(fontSize = 0.sp),
    button = TextStyle(fontSize = 0.sp),
    // This is changed since BottomNavigationItem has hardcoded the style for
    // `label` to be `MaterialTheme.typography.caption` setting this 0.dp will make it invisible to the user
    caption = TextStyle(fontSize = 10.sp),
    overline = TextStyle(fontSize = 0.sp)
)

/**
 * This function tries to match the provided [backgroundColor] to a 'background' color in this
 * [MoviesColors], and then will return the corresponding color used for content. For example, when
 * [backgroundColor] is [MoviesColors.uiBackgroundPrimary], this will return [MoviesColors.textSecondary].
 *
 * @return the matching content color for [backgroundColor]. If [backgroundColor] is not present in
 * the theme's [Colors], then returns [Color.Unspecified].
 *
 * @see contentColorFor
 */
fun MoviesColors.contentColorFor(backgroundColor: Color): Color {
    return when (backgroundColor) {
        uiBackgroundPrimary -> textPrimary
        uiBackgroundSecondary -> textSecondary
        uiBackgroundTertiary -> textSecondary
        uiFloated -> textSecondary
        else -> Color.Unspecified
    }
}

fun MoviesColors.contentColorForGradient(): Color = textSecondary

/**
 * This function tries to match the provided [backgroundColor] to a 'background' color in this
 * [MoviesColors], and then will return the corresponding color used for content. For example, when
 * [backgroundColor] is [MoviesColors.uiBackgroundPrimary], this will return [MoviesColors.textSecondary].
 *
 * @return the matching content color for [backgroundColor]. If [backgroundColor] is not present in
 * the theme's [Colors], then returns the current value of [LocalContentColor] as a best-effort color.
 *
 * @see MoviesColors.contentColorFor
 */
@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color) =
    MoviesAppTheme.colors.contentColorFor(backgroundColor).takeOrElse { LocalContentColor.current }
