package com.example.funcy_portfolio_android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funcy_portfolio_android.ui.theme.FuncyColor

/**
 * Button filled with Gradient(FuncyColor.gradientPink)
 * @param text is Button Text
 * @param onClick called when this button is clicked
 * @param gradientBrush is Type of Brush. Gradient apply on Button Container
 * @param textStyle
 */
@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    gradientBrush: Brush = FuncyColor.gradientPink,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box (
            modifier = modifier
                .background(brush = gradientBrush)
                .padding(horizontal = 24.dp, vertical = 10.dp)
            ,
        ){
            Text(
                text = text,
                color = FuncyColor.white,
                style = textStyle,
            )
        }
    }
}

/**
 *  Filled Button
 * @param text is Button Text
 * @param onClick called when this button is clicked
 * @param color is Button container Color
 * @param textColor is Button textColor
 * @param textStyle
 */
@Composable
fun InnerButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = FuncyColor.primary,
    textColor: Color = FuncyColor.white,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
        )
    }
}


/**
 * Bordered Button filled with FuncyColor.white
 * @param text is Button Text
 * @param onClick called when this button is clicked
 * @param color is Button container Color
 * @param borderColor is boderLine's Color
 * @param textColor is Button Text Color
 * @param textStyle
 */
@Composable
fun BorderButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = FuncyColor.white,
    borderColor: Color = FuncyColor.primary,
    textColor: Color = FuncyColor.primary,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    OutlinedButton(
        border = BorderStroke(
            width = 1.dp,
            color = borderColor,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
        )
    }
}

/**
 * Text Button with FuncyColor.primary text
 * @param text is Button Text
 * @param onClick called when this button is clicked
 */
@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color = FuncyColor.primary,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
){
    androidx.compose.material3.TextButton(
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
        )
    }
}

@Preview
@Composable
fun PreviewButtons() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GradientButton(text = "テスト", onClick = {})
        InnerButton(text = "テスト", onClick = {})
        BorderButton(text = "テスト", onClick = {})
        TextButton(text = "テスト", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGradientInnerButton() {
    GradientButton(text = "テスト", onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewInnerButton() {
    InnerButton(text = "テスト", onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewBorderButton() {
    BorderButton(text = "テスト", onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewTextButton() {
    TextButton(text = "テスト", onClick = {})
}
