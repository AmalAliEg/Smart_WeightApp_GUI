package com.example.al_awal.ui.utilies
import android.content.Context
import android.os.BatteryManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.BatteryStd
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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