package com.example.al_awal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.al_awal.ui.screens.Automation_mode
import com.example.al_awal.ui.screens.Manual_mode
import com.example.al_awal.ui.screens.Login_screen
import com.example.al_awal.ui.screens.Modes_screen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") { Login_screen(navController) }
        composable("Modes") { Modes_screen(navController) }
        composable("Automation") { Automation_mode(navController) }
        composable("Manual") { Manual_mode(navController) }
    }
}