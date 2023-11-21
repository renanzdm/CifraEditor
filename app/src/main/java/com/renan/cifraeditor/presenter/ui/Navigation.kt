package com.renan.cifraeditor.presenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.renan.cifraeditor.presenter.addciphers.AddCipherPage
import com.renan.cifraeditor.presenter.soundcontrol.SoundControlPage

sealed class AppRoutes {
    companion object {
        const val addCipherRoute: String = "add-cipher"
        const val soundControlRoute: String = "sound-control"

    }
}

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(navController = navController, startDestination = AppRoutes.addCipherRoute) {
        composable(AppRoutes.addCipherRoute) { AddCipherPage() }
        composable(AppRoutes.soundControlRoute) { SoundControlPage(navController = navController) }
    }
}


//fun NavGraphBuilder.homeGraph(navController: NavHostController) {
//
//
//}