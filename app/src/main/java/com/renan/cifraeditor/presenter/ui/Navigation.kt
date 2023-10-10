package com.renan.cifraeditor.presenter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.renan.cifraeditor.presenter.addciphers.AddCipherPage
import com.renan.cifraeditor.presenter.home.HomePage

sealed class AppRoutes {
    companion object {
        const val splashRoute: String = "home"
        const val homeRoute: String = "home"
        const val addCipherRoute: String = "add-cipher"
    }
}

@Composable
fun AppNavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = AppRoutes.splashRoute) {
        homeGraph(navController = navController)
    }
}


fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(AppRoutes.homeRoute) { HomePage(navController = navController) }
    composable(AppRoutes.addCipherRoute) { AddCipherPage(navController = navController) }

}