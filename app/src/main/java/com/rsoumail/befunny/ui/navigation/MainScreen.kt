package com.rsoumail.befunny.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    AppNavGraph(
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
    )
}