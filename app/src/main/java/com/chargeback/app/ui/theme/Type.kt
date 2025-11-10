package com.chargeback.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chargeback.app.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val graphikTrialFontFamily = FontFamily(
    Font(R.font.graphik_trial_thin, FontWeight.Thin),
    Font(R.font.graphik_trial_extra_light, FontWeight.ExtraLight),
    Font(R.font.graphik_trial_light, FontWeight.Light),
    Font(R.font.graphik_trial_regular, FontWeight.Normal),
    Font(R.font.graphik_trial_medium, FontWeight.Medium),
    Font(R.font.graphik_trial_semi_bold, FontWeight.SemiBold),
    Font(R.font.graphik_trial_bold, FontWeight.Bold),
    Font(R.font.graphik_trial_black, FontWeight.ExtraBold),
    Font(R.font.graphik_trial_super, FontWeight.Black),
)