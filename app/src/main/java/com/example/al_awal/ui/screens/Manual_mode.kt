package com.example.al_awal.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.al_awal.R

@Composable
fun ImageSlider(images: List<Int>) {
    var sliderPosition by remember { mutableStateOf(0f) }

    // Calculate the current image index based on the slider position
    val imageIndex = (sliderPosition * (images.size - 1)).toInt()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Display the current image
            Image(
                painter = painterResource(id = images[imageIndex]),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Slider to navigate through images
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0f..1f,
                steps = images.size - 1
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Manual_mode(navController: NavController) {
    // Correctly declare the list of drawable resources
    val images: List<Int> = listOf(
        R.drawable.apple,
        R.drawable.banana,
        R.drawable.cabbage,
        R.drawable.eggplant,
        R.drawable.grape,
        R.drawable.orange,
        R.drawable.pumpkin,
        R.drawable.radish,
        R.drawable.strawberry,
        R.drawable.tomato
    )

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

                // Add the ImageSlider here
                ImageSlider(images = images)

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    )
}