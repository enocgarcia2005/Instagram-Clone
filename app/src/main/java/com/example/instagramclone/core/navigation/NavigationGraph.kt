package com.example.instagramclone.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.core.navigation.anonymous.anonymousNavGraph
import com.example.instagramclone.core.navigation.authenticated.authenticatedNavGraph

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavHostType.ANONYMOUS.ROUTE,
    ){
        anonymousNavGraph(navController)
        authenticatedNavGraph(navController)
    }

}
