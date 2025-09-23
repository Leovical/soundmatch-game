package com.example.soundmatch.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.soundmatch.R

// silkscreen
val SilkscreenFamily = FontFamily(
    Font(R.font.silkscreen_regular, FontWeight.Normal),
    Font(R.font.silkscreen_bold, FontWeight.Bold)
)

// other family fonts
val PressStart2PFamily = FontFamily(
    Font(R.font.press_start_2p, FontWeight.Normal)
)

val VT323Family = FontFamily(
    Font(R.font.vt323, FontWeight.Normal)
)

// object Typography, our fonts
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = PressStart2PFamily,
        fontWeight = FontWeight.Normal, // one weight
        fontSize = 40.sp
    ),
    // titles and sections, bold one
    titleLarge = TextStyle(
        fontFamily = SilkscreenFamily,
        fontWeight = FontWeight.Bold, //silkscreen_bold.ttf
        fontSize = 22.sp
    ),
    // regular
    bodyLarge = TextStyle(
        fontFamily = SilkscreenFamily,
        fontWeight = FontWeight.Normal, // silkscreen_regular.ttf
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    //  VT323
    labelLarge = TextStyle(
        fontFamily = VT323Family,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
)