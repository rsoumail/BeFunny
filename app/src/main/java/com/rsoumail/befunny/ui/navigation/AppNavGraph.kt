package com.rsoumail.befunny.ui.navigation

import android.net.Uri
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.rsoumail.befunny.core.ui.extension.navigate
import com.rsoumail.befunny.core.ui.navigation.Screen
import com.rsoumail.befunny.feature.funnyreal.R
import com.rsoumail.befunny.feature.funnyreal.ui.common.FUNNY_LOCATION_ARG
import com.rsoumail.befunny.feature.funnyreal.ui.player.PlayerRoute
import com.rsoumail.befunny.feature.funnyreal.ui.recorder.RecorderRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Screen.FunnyRecorder.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
            .fillMaxSize()
    ) {
        composable(route = Screen.FunnyRecorder.route) {
            RecorderRoute(
                navigateToPlayer = { funnyUrl ->
                    navController.navigate(
                        route = Screen.FunnyPlayer.route,
                        args = bundleOf(
                            FUNNY_LOCATION_ARG to funnyUrl
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.FunnyPlayer.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "app://new-funny"
            })
        ) { backStackEntry ->

            var isFromRecorder by remember {
                mutableStateOf(false)
            }

            val funnyLocation by remember {
                backStackEntry.arguments?.getString(FUNNY_LOCATION_ARG)?.let { location ->
                    isFromRecorder = true
                    mutableStateOf(location)
                } ?: mutableStateOf(
                    Uri.parse("android.resource://${context.packageName}/${R.raw.sample_funny}").toString()
                )
            }

            PlayerRoute(
                funnyLocation,
                onCancelNavigation = if (isFromRecorder) {
                    {
                        navController.popBackStack(Screen.FunnyRecorder.route, inclusive = false)
                    }
                } else {
                    {
                        navController.navigate(
                            route = Screen.FunnyRecorder.route
                        )
                    }
                },
                onConfirmChallenge = { navController.navigate(Screen.FunnyRecorder.route) },
                isFromRecorder = isFromRecorder
            )
        }
    }
}