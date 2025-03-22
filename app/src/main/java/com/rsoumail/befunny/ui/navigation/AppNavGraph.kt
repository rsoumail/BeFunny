package com.rsoumail.befunny.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rsoumail.befunny.core.ui.navigation.Screen
import com.rsoumail.befunny.feature.funnyreal.ui.recorder.FunnyRecorderRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FunnyRecorder.route,
        modifier = modifier
            .fillMaxSize()
    ) {
        composable(route = Screen.FunnyRecorder.route) {
            FunnyRecorderRoute(
                navigateToPlayer = { funnyUrl ->

                }
            )
        }
    }
}