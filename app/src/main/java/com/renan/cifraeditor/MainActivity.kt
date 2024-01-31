package com.renan.cifraeditor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.CifraEditorTheme
import com.renan.cifraeditor.presenter.ui.AppNavigationHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController: NavHostController = rememberNavController()
            CifraEditorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {  paddingValues ->
                        AppNavigationHost(
                            navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomButtons(navController: NavController) {
    val screens = listOf(
        ScreensBottomBars.AddCifras, ScreensBottomBars.SoundControl
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(painter = painterResource(id = screen.icon!!), contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}


sealed class ScreensBottomBars(
    val route: String, val title: String? = null, val icon: Int? = null
) {
    data object HomeScreen : ScreensBottomBars(
        route = "home", title = "Home", icon = R.drawable.baseline_home_24
    )

    data object AddCifras : ScreensBottomBars(
        route = "add-cipher", title = "Cifras", icon = R.drawable.baseline_music_note_24
    )

    data object SoundControl : ScreensBottomBars(
        route = "sound-control", title = "Controle de Som", icon = R.drawable.baseline_equalizer_24
    )

}