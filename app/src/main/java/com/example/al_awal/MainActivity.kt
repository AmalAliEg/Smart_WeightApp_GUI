package com.example.al_awal
import com.example.al_awal.ui.navigation.Navigation
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
import androidx.compose.ui.graphics.graphicsLayer
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


@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    AlawalTheme {
        val navController = rememberNavController()
        Navigation(navController)
    }
}