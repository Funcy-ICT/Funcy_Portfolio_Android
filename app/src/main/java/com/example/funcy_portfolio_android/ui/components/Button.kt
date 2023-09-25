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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funcy_portfolio_android.ui.theme.FuncyColor

@Composable
fun InnerButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    val gradient = FuncyColor.gradientPink

    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box (
            modifier = modifier
                .background(brush = gradient)
                .padding(horizontal = 24.dp, vertical = 10.dp)
            ,
        ){
            Text(
                text = text,
                color = FuncyColor.white,
            )
        }
    }
}

@Composable
fun BorderButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        border = BorderStroke(
            width = 1.dp,
            color = FuncyColor.primary),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = FuncyColor.text,
        )
    }
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
){
    androidx.compose.material3.TextButton(
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = FuncyColor.primary,
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
        InnerButton(text = "テスト", onClick = {})
        BorderButton(text = "テスト", onClick = {})
        TextButton(text = "テスト", onClick = {})
    }
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
