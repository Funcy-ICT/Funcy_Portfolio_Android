package com.example.funcy_portfolio_android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Destination.MainScreen.route){
            TestScreen(text = Destination.MainScreen.route)
        }
        composable(route = Destination.MypageScreen.route){
            TestScreen(text = Destination.MypageScreen.route)
        }
        composable(route = Destination.GroupMypageScreen.route){
            TestScreen(text = Destination.GroupMypageScreen.route)
        }
        composable(route = Destination.SettingScreen.route){
            TestScreen(text = Destination.SettingScreen.route)
        }
    }
}

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
        )
    }
}