package com.example.movieskmm.android.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.movieskmm.android.R

val moderatFontFamily = FontFamily(
    Font(R.font.moderat_black, weight = FontWeight.Bold),
    Font(R.font.moderat_black_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.moderat_bold, weight = FontWeight.SemiBold),
    Font(R.font.moderat_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.moderat_regular, weight = FontWeight.Normal),
    Font(R.font.moderat_regular_italic, weight = FontWeight.Normal, style = FontStyle.Italic)
)

val Typography = MoviesTypography(
    display9 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 120.sp,
        color = Color.Black,
        lineHeight = 120.sp
    ),
    display8 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 80.sp,
        color = Color.Black,
        letterSpacing = TextUnit(1.6f, TextUnitType.Sp),
        lineHeight = 80.sp
    ),
    display7 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 60.sp,
        color = Color.Black,
        letterSpacing = TextUnit(1.2f, TextUnitType.Sp),
        lineHeight = 64.sp
    ),
    heading7 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        lineHeight = 64.sp
    ),
    display6 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 48.sp,
        color = Color.Black,
        fontWeight = FontWeight.Black,
        letterSpacing = TextUnit(0.96f, TextUnitType.Sp),
        lineHeight = 52.sp
    ),
    heading6 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        lineHeight = 52.sp
    ),
    display5 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 32.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        letterSpacing = TextUnit(1.6f, TextUnitType.Sp),
        lineHeight = 36.sp
    ),
    heading5 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 32.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    ),
    display4 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        letterSpacing = TextUnit(1.2f, TextUnitType.Sp),
        lineHeight = 32.sp
    ),
    heading4 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.2.sp,
        lineHeight = 32.sp
    ),
    body4 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp
    ),
    headingSerif4 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 24.sp,
        color = Color.Black,
        letterSpacing = TextUnit(-0.48f, TextUnitType.Sp),
        lineHeight = 32.sp
    ),
    display3 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        lineHeight = 28.sp
    ),
    heading3 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp
    ),
    display2 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        letterSpacing = TextUnit(0.8f, TextUnitType.Sp),
        lineHeight = 24.sp
    ),
    heading2 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),
    display1 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 14.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        letterSpacing = TextUnit(1.4f, TextUnitType.Sp),
        lineHeight = 20.sp
    ),
    heading1 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 14.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = moderatFontFamily,
        fontSize = 14.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )
)

@Immutable
data class MoviesTypography(
    val display9: TextStyle,
    val display8: TextStyle,
    val display7: TextStyle,
    val display6: TextStyle,
    val display5: TextStyle,
    val display4: TextStyle,
    val display3: TextStyle,
    val display2: TextStyle,
    val display1: TextStyle,
    val heading7: TextStyle,
    val heading6: TextStyle,
    val heading5: TextStyle,
    val heading4: TextStyle,
    val heading3: TextStyle,
    val heading2: TextStyle,
    val heading1: TextStyle,
    val body4: TextStyle,
    val body2: TextStyle,
    val body1: TextStyle,
    val headingSerif4: TextStyle
)

inline val LocalTypography
    get() = compositionLocalOf { Typography }
