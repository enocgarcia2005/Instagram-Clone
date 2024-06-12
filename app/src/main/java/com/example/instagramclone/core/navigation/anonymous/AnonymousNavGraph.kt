package com.example.instagramclone.core.navigation.anonymous

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instagramclone.auth.presentation.login.LoginScreen
import com.example.instagramclone.core.navigation.NavHostType

fun NavGraphBuilder.anonymousNavGraph(
    navController: NavHostController,
) {
    navigation(startDestination = AnonymousScreens.LoginScreen.ROUTE, route = NavHostType.ANONYMOUS.ROUTE) {
        composable(route = AnonymousScreens.LoginScreen.ROUTE){
            LoginScreen(navController = navController)
        }
    }
}