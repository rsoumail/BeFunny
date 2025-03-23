package com.rsoumail.befunny.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.rsoumail.befunny.ui.navigation.AppNavGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = koinViewModel()
    AppNavGraph(
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
    )
}