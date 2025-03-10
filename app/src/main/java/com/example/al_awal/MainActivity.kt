package com.example.al_awal

import android.content.Context
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.BatteryStd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.al_awal.ui.theme.AlawalTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlawalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2(navController) }
        composable("screen3") { Screen3(navController) }
    }
}

@Composable
fun DateTimeBatteryTab() {
    val context = LocalContext.current
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    var batteryLevel by remember { mutableStateOf(getBatteryLevel(context)) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Update every second
            currentTime = getCurrentTime()
            batteryLevel = getBatteryLevel(context)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = currentTime)
        BatteryIcon(batteryLevel = batteryLevel)
    }
}

@Composable
fun BatteryIcon(batteryLevel: Int) {
    val batteryIcon = when {
        batteryLevel >= 75 -> Icons.Filled.BatteryFull
        batteryLevel >= 25 -> Icons.Filled.BatteryStd
        else -> Icons.Filled.BatteryAlert
    }

    Icon(
        imageVector = batteryIcon,
        contentDescription = "Battery Icon",
        modifier = Modifier.size(24.dp)
    )
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}

fun getBatteryLevel(context: Context): Int {
    val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController) {
    Scaffold(
        topBar = {
            Column {
                DateTimeBatteryTab()
                CenterAlignedTopAppBar(title = { Text("Welcome to the Smart Weight-App <3") })
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
                Text("This is Screen 1", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("screen2") }) {
                    Text("Automation Mode")
                }
                Button(onClick = { navController.navigate("screen3") }) {
                    Text("Manual Mode")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(navController: NavController) {
    var sliderPosition by remember { mutableStateOf(0f) }
    val texts = listOf("Tomato", "Potato", "Avocado","onion","Apple","Orange","Grallic") // Your texts for the slider

    Scaffold(
        topBar = {
            Column {
                DateTimeBatteryTab()
                CenterAlignedTopAppBar(title = { Text("Automation mode") })
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Fill width for alignment
                        .padding(16.dp) // Add padding around the Box
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.CenterStart) // Align to left-center
                    ) {
                        Slider(
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it },
                            valueRange = 0f..7f, // Range corresponding to the number of texts
                            steps = 1 // Number of steps between texts - 1 less than the number of texts
                        )

                        Spacer(modifier = Modifier.height(8.dp)) // Space between slider and text

                        Text(text = texts[sliderPosition.toInt()]) // Display the selected text
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(navController: NavController) {
    Scaffold(
        topBar = {
            Column {
                DateTimeBatteryTab()
                CenterAlignedTopAppBar(title = { Text("Manual Mode") })
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
                Text("This is Screen 3", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    AlawalTheme {
        val navController = rememberNavController()
        Navigation(navController)
    }
}