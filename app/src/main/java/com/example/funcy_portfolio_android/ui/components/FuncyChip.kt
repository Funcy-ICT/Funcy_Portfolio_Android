package com.example.funcy_portfolio_android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funcy_portfolio_android.ui.theme.FuncyColor
import com.example.funcy_portfolio_android.ui.theme.FuncyTypography


/**
 * SearchChipはメイン画面のChipでタップすると色が変化し，チェックアイコンが出現する
 * @param text Chipに表示されるテキスト内容
 * @param isSelected Chipが選択されているかどうか状態を監視する
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val checkedChipColor = FilterChipDefaults.filterChipColors(
        selectedContainerColor = FuncyColor.primary,
        selectedLabelColor = FuncyColor.white,
        selectedLeadingIconColor = FuncyColor.white
    )
    FilterChip(
        onClick = { onClick() },
        selected = isSelected,
        label = {
            if (isSelected) {
                Text(
                    text = "#" + text,
                    overflow = TextOverflow.Ellipsis,
                    style = FuncyTypography.titleSmall,
                )
            } else {
                Text(
                    text = "#" + text,
                    overflow = TextOverflow.Ellipsis,
                    style = FuncyTypography.titleSmall,
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
    modifier: Modifier = Modifier,
    text: String,
    isEditable: Boolean,
    onClick: () -> Unit,
) {
    val checkedChipColor = InputChipDefaults.inputChipColors(
        selectedContainerColor = FuncyColor.white
    )
    val checkedChipBorder = InputChipDefaults.inputChipBorder(
        selectedBorderColor = FuncyColor.text,
        selectedBorderWidth = 1.dp
    )
    InputChip(
        onClick = { onClick() },
        selected = isEditable,
        label = {
            Text(
                text = "#" + text,
                style = FuncyTypography.titleSmall,
            )
                },
        trailingIcon = {
            if (isEditable) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    modifier.size(InputChipDefaults.AvatarSize)
                )
            } else {
                null
            }
        },
        colors = checkedChipColor,
        border = checkedChipBorder
    )
}


@Preview(showBackground = true)
@Composable
fun SearchChipPreview() {
    SearchChip(text = "kotlin", isSelected = true, onClick = {})
}


@Preview(showBackground = true)
@Composable
fun SkillChipPreview() {
    Column {
        SkillChip(text = "kotlin", onClick = {}, isEditable = false)
        SkillChip(text = "kotlin", onClick = {}, isEditable = true)
    }
}
