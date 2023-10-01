package com.example.funcy_portfolio_android.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.funcy_portfolio_android.R

/**
 * font: Noto Sans Japanese
 *
 * CAUTION!: Fonts are subject to change
 */
val notoSansFamily = FontFamily(
    Font(R.font.notosansjp_variablefont_wght)

)

/**
 * Funcy Typography
 *
 * type: Headline, Title, Body, Label
 *
 * Please refer to Notion where each type is applied
 */
object FuncyTypography {

    val headlineLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val headlineMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val titleLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val titleMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val titleSmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val labelLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val labelMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )

    val labelSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontFamily = notoSansFamily
    )
}
