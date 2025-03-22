package com.rsoumail.befunny.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rsoumail.befunny.core.ui.extension.navigate
import com.rsoumail.befunny.core.ui.navigation.Screen
import com.rsoumail.befunny.feature.funnyreal.ui.common.FUNNY_URL_ARG
import com.rsoumail.befunny.feature.funnyreal.ui.player.FunnyPlayerRoute
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
                    navController.navigate(
                        route = Screen.FunnyPlayer.route,
                        args = bundleOf(
                            FUNNY_URL_ARG to funnyUrl
                        )
                    )
                }
            )
        }

        composable(route = Screen.FunnyPlayer.route) {
            FunnyPlayerRoute(
                it.arguments?.getString(FUNNY_URL_ARG) ?: "",
                onCancelNavigation = {
                    /*navController.navigate(
                        route = Screen.FunnyRecorder.route
                    )*/
                    navController.popBackStack()
                }
            )
        }
    }
}