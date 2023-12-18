package com.example.funcy_portfolio_android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val route: String,
    val selectedIcon: ImageVector = Icons.Filled.Build,
    val icon: ImageVector = Icons.Outlined.Build,
) {
    object MainScreen : Destination(
        route = "main_screen",
        selectedIcon = Icons.Filled.Search,
        icon = Icons.Outlined.Search,
    )

    object MypageScreen : Destination(
        route = "mypage_screen",
        selectedIcon = Icons.Filled.Person,
        icon = Icons.Outlined.Person,
    )

    object GroupMypageScreen : Destination(
        route = "gropu_mypage_screen",
        selectedIcon = Icons.Filled.Groups,
        icon = Icons.Outlined.Groups,
    )
    object SettingScreen : Destination(
        route = "setting_screen",
        selectedIcon = Icons.Filled.Settings,
        icon = Icons.Outlined.Settings,
    )
}

val bottomScreens = listOf(
    Destination.MainScreen,
    Destination.MypageScreen,
    Destination.GroupMypageScreen,
    Destination.SettingScreen,
)