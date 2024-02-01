package com.renan.cifraeditor.presenter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.renan.cifraeditor.presenter.cipherdetails.CipherDetailsPage
import com.renan.cifraeditor.presenter.addciphers.AddCipherPage
import com.renan.cifraeditor.presenter.home.HomePage

sealed class AppRoutes {
    companion object {
        const val addCipherRoute: String = "add-cipher"
        const val cipherDetailsRoute: String = "cipher-details-route/{id}"
        const val homeRoute: String = "home-route"
        fun <T> replaceParam(route:String,value:T,nameParam:String): String {
            return route.replace(oldValue = "{$nameParam}", newValue = value.toString())
        }
    }


}

@Composable
fun AppNavigationHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = AppRoutes.homeRoute) {
        composable(AppRoutes.homeRoute) { HomePage(navController = navController) }
        composable(AppRoutes.addCipherRoute) { AddCipherPage(navController = navController) }
        composable(
            AppRoutes.cipherDetailsRoute,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            CipherDetailsPage(idCipher = it.arguments?.getLong("id"), navController = navController)
        }
    }
}


//fun NavGraphBuilder.homeGraph(navController: NavHostController) {
//
//
//}