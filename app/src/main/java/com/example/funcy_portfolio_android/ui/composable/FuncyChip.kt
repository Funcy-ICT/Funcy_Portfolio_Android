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


/**
 * SearchChipはメイン画面のChipでタップすると色が変化し，チェックアイコンが出現する
 * @param text Chipに表示されるテキスト内容
 * @param isSelected Chipが選択されているかどうか状態を監視する
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    var isSelected by remember { mutableStateOf(isSelected) }
    val checkedChipColor = FilterChipDefaults.filterChipColors(
        selectedContainerColor = Color(0xFFFFC2E2),
        selectedLabelColor = Color(0xFF3b4043),
        selectedLeadingIconColor = Color(0xFF3b4043)
    )
    FilterChip(
        onClick = { isSelected = !isSelected },
        selected = isSelected,
        label = { Text("#" + text, overflow = TextOverflow.Ellipsis) },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    Modifier.size(InputChipDefaults.IconSize)
                )
            }
        },
        colors = if (isSelected) {
            checkedChipColor
        } else {
            FilterChipDefaults.filterChipColors()
        }
    )
}

/**
 * SkillChipはマイページや作品投稿画面のChipで作成や編集が可能になっている
 * @param text Chipに表示されるテキスト内容
 * @param isEditable Chipが編集可能かどうかの状態を監視する
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillChip(
    text: String,
    isEditable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var editable by remember { mutableStateOf(isEditable) }
    val checkedChipColor = InputChipDefaults.inputChipColors(
        selectedContainerColor = Color(0xFFFFFFFF)
    )
    val checkedChipBorder = InputChipDefaults.inputChipBorder(
        selectedBorderColor = Color(0xFF3b4043),
        selectedBorderWidth = 1.dp
    )
    InputChip(
        onClick = { onClick() },
        selected = editable,
        label = { Text("#" + text) },
        trailingIcon = {
            if (editable) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            } else {
                null
            }
        },
        colors = if (editable) {
            checkedChipColor
        } else {
            InputChipDefaults.inputChipColors()
        },
        border = if (editable) {
            checkedChipBorder
        } else {
            InputChipDefaults.inputChipBorder()
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SearchChipPreview() {
    SearchChip(text = "kotlin", isSelected = false)
}


@Preview(showBackground = true)
@Composable
fun SkillChipPreview() {
    Column {
        SkillChip(text = "kotlin", onClick = {}, isEditable = false)
        SkillChip(text = "kotlin", onClick = {}, isEditable = true)
    }
}
