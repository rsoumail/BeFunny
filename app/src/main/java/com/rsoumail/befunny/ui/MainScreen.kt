package com.rsoumail.befunny.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.rsoumail.befunny.ui.navigation.AppNavGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        mainViewModel.setupNotificationSimulator()
    }

    AppNavGraph(
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
    )
}