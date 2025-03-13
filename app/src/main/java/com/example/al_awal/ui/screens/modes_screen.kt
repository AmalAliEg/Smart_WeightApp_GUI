package com.example.al_awal.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.al_awal.ui.utilies.DateTimeBatteryTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modes_screen(navController: NavController) {
    Scaffold(
        topBar = {
            Column {
                DateTimeBatteryTab()
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("This screen to choose which mode", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("Automation") }) {
                    Text("Automation Mode")
                }
                Button(onClick = { navController.navigate("Manual") }) {
                    Text("Manual Mode")
                }
            }
        }
    )
}