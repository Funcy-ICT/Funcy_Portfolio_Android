package com.example.funcy_portfolio_android.ui.theme

import androidx.compose.ui.graphics.Brush

/***
 * Funcy Color Pallete
 * usage ex. color=FuncyColor.primary
 * CAUTION! gradientPink 's Type is Brush()
 */
object FuncyColor {
    val primary = Primary
    val sub = Sub
    val secondary = Secondary
    val gradientPink = Brush.verticalGradient(colors = listOf(Primary, GradientPink))
    val text = TextColor
    val subText = SubTextColor
    val white = White
    val gray = Gray
    val backgroundGray = BackGroundGray
    val smoke = Smoke
}
