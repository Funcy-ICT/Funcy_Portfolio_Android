package com.example.funcy_portfolio_android.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// chipをタップすると，色が変化し，アイコンが出現する
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchChip(content: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    var chekced by remember { mutableStateOf(false) }
    val checkedChipColor = FilterChipDefaults.filterChipColors(
        selectedContainerColor = Color(0xFFFFC2E2),
        selectedLabelColor = Color(0xFF3b4043),
        selectedLeadingIconColor = Color(0xFF3b4043)
    )
    FilterChip(
        onClick = { chekced = !chekced },
        selected = chekced,
        label = { Text(content, overflow = TextOverflow.Ellipsis) },
        leadingIcon = {
            if (chekced) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    Modifier.size(InputChipDefaults.IconSize)
                )
            }
        },
        colors = if (chekced) {
            checkedChipColor
        } else {
            FilterChipDefaults.filterChipColors()
        }
    )
}


// editStateはタグ編集判定のための変数
// true -> ✕アイコンが出現
// false -> 普通のchip
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillChip(
    content: String,
    onClick: () -> Unit,
    editState: Boolean,
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }
    val checkedChipColor = InputChipDefaults.inputChipColors(
        selectedContainerColor = Color(0xFFFFFFFF)
    )
    val checkedChipBorder = InputChipDefaults.inputChipBorder(
        selectedBorderColor = Color(0xFF3b4043),
        selectedBorderWidth = 1.dp
    )
    InputChip(
        onClick = { checked = !checked },
        selected = checked,
        label = { Text(content) },
        trailingIcon = {
            if (editState) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            } else {
                null
            }
        },
        colors = if (checked) {
            checkedChipColor
        } else {
            InputChipDefaults.inputChipColors()
        },
        border = if (checked) {
            checkedChipBorder
        } else {
            InputChipDefaults.inputChipBorder()
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SearchChipPreview() {
    SearchChip(content = "#kotlin", onClick = {})
}


@Preview(showBackground = true)
@Composable
fun SkillChipPreview() {
    Column {
        SkillChip(content = "#kotlin", onClick = {}, editState = false)
        SkillChip(content = "#kotlin", onClick = {}, editState = true)
    }
}
