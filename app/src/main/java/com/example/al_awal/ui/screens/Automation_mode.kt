package com.example.al_awal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.al_awal.R
import com.example.al_awal.ui.utilies.DateTimeBatteryTab


@Composable
fun Weight_Label(weight: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "%.1f".format(weight),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Slider_text(
    sliderPosition: Float,
    onSliderPositionChange: (Float) -> Unit,
    sliderLabels: List<String>,
    modifier: Modifier = Modifier
) {
    Slider(
        value = sliderPosition,
        onValueChange = { newValue ->
            onSliderPositionChange(newValue.coerceIn(0f, sliderLabels.size - 1f))
        },
        valueRange = 0f..(sliderLabels.size - 1).toFloat(),
        steps = sliderLabels.size - 2,
        modifier = modifier.width(100.dp)
    )
}

@Composable
fun Slider_Figures(
    sliderPosition: Float,
    onSliderPositionChange: (Float) -> Unit,
    sliderImages: List<Int>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { newValue ->
                onSliderPositionChange(newValue.coerceIn(0f, sliderImages.size - 1f))
            },
            valueRange = 0f..(sliderImages.size - 1).toFloat(),
            steps = sliderImages.size - 2,
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = sliderImages[sliderPosition.toInt()]),
            contentDescription = "Selected Fruit", // Provide a content description
            modifier = Modifier.size(100.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Automation_mode(navController: NavController) {
    var sliderPosition by remember { mutableStateOf(0f) }
    val sliderLabels = listOf("Onions", "Orange", "Apple", "Potato")
    val sliderImages = listOf(
        R.drawable.onion,
        R.drawable.orange,
        R.drawable.apple,
        R.drawable.potato
    )

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp


    Scaffold(
        topBar = {
            Column {
                DateTimeBatteryTab()
                CenterAlignedTopAppBar(title = { Text("Automation mode") })
            }
        },
        content = { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(screenWidth * 0.2f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Slider_text(
                        sliderPosition = sliderPosition,
                        onSliderPositionChange = { newValue -> sliderPosition = newValue },
                        sliderLabels = sliderLabels,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = sliderLabels[sliderPosition.toInt()])
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("This is Screen 2", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(16.dp))

                    Weight_Label(
                        weight = 4.554f,
                        modifier = Modifier.size(screenWidth * 0.3f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Slider_Figures(
                        sliderPosition = sliderPosition,
                        onSliderPositionChange = { newValue -> sliderPosition = newValue },
                        sliderImages = sliderImages,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Go Back")
                    }
                }
            }
        }
    )
}