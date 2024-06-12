package com.example.instagramclone.core.navigation.authenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.core.navigation.NavHostType
import com.example.instagramclone.home.presentation.HomeScreen

fun NavGraphBuilder.authenticatedNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = AuthenticatedScreens.HomeScreen.ROUTE, route = NavHostType.AUTHENTICATED.ROUTE) {
        composable(route = AuthenticatedScreens.HomeScreen.ROUTE) {
            HomeScreen(navController = navController)
        }
    }
}
