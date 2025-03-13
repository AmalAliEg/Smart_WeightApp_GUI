package com.example.al_awal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.al_awal.ui.theme.AlawalTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.al_awal.ui.screens.Automation_mode
import com.example.al_awal.ui.screens.Manual_mode
import com.example.al_awal.ui.screens.Login_screen
import com.example.al_awal.ui.screens.Modes_screen

class MainActivity : ComponentActivity() {
    //override the standard/basic OnCreate func of android and apply the OnCreate func related to this app
    override fun onCreate(savedInstanceState: Bundle?) {
        //call tha main class "ComponentActivity" to be certain that execution is done correctly
        super.onCreate(savedInstanceState)
        setContent {
            //specific theme for the app, without it, app would use the basic theme for android
            AlawalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //start to display and show the GUI
                    Navigation()
                }
            }
        }
    }
}


@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") { Login_screen(navController) }
        composable("Modes") { Modes_screen(navController) }
        composable("Automation") { Automation_mode(navController) }
        composable("Manual") { Manual_mode(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    AlawalTheme {
        val navController = rememberNavController()
        Navigation(navController)
    }
}