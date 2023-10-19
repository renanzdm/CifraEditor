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
import com.renan.cifraeditor.presenter.soundcontrol.SoundControlPage

sealed class AppRoutes {
    companion object {
        const val homeRoute: String = "home"
        const val addCipherRoute: String = "add-cipher"
        const val soundControlRoute: String = "soundControl"

    }
}

@Composable
fun AppNavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = AppRoutes.homeRoute) {
        homeGraph(navController = navController)
    }
}


fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(AppRoutes.homeRoute) { HomePage(navController = navController) }
    composable(AppRoutes.addCipherRoute) { AddCipherPage(navController = navController) }
    composable(AppRoutes.soundControlRoute) { SoundControlPage(navController = navController) }

}