package com.rsoumail.befunny.core.ui.extension

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val nodeId = graph.findNode(route = route)?.id
    require(nodeId != null) {
        "route $route not found in the current graph"
    }
    navigate(nodeId, args, navOptions, navigatorExtras)
}