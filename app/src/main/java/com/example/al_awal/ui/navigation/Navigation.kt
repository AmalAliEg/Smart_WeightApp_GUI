package com.example.al_awal.ui.navigation
import com.example.al_awal.ui.screens.login_screen
import com.example.al_awal.ui.screens.modes_screen
import com.example.al_awal.ui.screens.Automation_mode
import com.example.al_awal.ui.screens.Manual_mode

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") { login_screen(navController) }
        composable("screen1") { modes_screen(navController) }
        composable("screen2") { Automation_mode(navController) }
        composable("screen3") { Manual_mode(navController) }
    }
}