package com.example.al_awal.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.al_awal.R // Import the R class
import androidx.navigation.NavController
import com.example.al_awal.ui.utilies.DateTimeBatteryTab
import kotlin.collections.listOf
import kotlin.ranges.rangeTo

@Composable
fun Weight_Label(weight: Float)
{
    //1-draw a circullar label to show float number
    Box (modifier = Modifier
        .size(150.dp)
        //edit the shape of the label
        .clip(CircleShape)
        //edit the background theme
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
    //edit the style of the font showed in the label
        Text (
            text = "%.1f".format(weight), // Format the float to one decimal place
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
    sliderLabels: List<String>
) {
    Slider(
        value = sliderPosition,
        onValueChange = { newValue ->
            // Ensure the new value stays within the valid range
            onSliderPositionChange(newValue.coerceIn(0f, sliderLabels.size - 1f))
        },
        valueRange = 0f..(sliderLabels.size - 1).toFloat(),
        steps = sliderLabels.size - 2,
        modifier = Modifier.width(100.dp) // Set the width of the slider
    )
}

@Composable
fun Slider_Figures(
    sliderPosition: Float,
    onSliderPositionChange: (Float) -> Unit,
    sliderImages: List<Int> // List of image resource IDs
) {
    Slider(
        value = sliderPosition,
        onValueChange = { newValue ->
            // Ensure the new value stays within the valid range
            onSliderPositionChange(newValue.coerceIn(0f, sliderImages.size - 1f))
        },
        valueRange = 0f..(sliderImages.size - 1).toFloat(),
        steps = sliderImages.size - 2,
        modifier = Modifier.width(100.dp) // Set the width of the slider
    )
    // Display the selected image
    Image(
        painter = painterResource(id = sliderImages[sliderPosition.toInt()]),
        contentDescription = null, // Provide a proper description for accessibility
        modifier = Modifier.size(100.dp)
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Automation_mode(navController: NavController) {
// Define the state and labels at a higher level
    var sliderPosition by remember { mutableStateOf(0f) }
    val sliderLabels = listOf("Onions", "Orange", "Apple", "Potato")

    //list of figures
    val sliderImages = listOf(
        R.drawable.onion, // Replace with your image resource IDs
        R.drawable.orange,
        R.drawable.apple,
        R.drawable.potato
    )

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
                // Slider on the left side
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Pass the state and labels to the Slider_text composable
                    Slider_text(
                        sliderPosition = sliderPosition,
                        onSliderPositionChange = { newValue ->
                            sliderPosition = newValue
                        },
                        sliderLabels = sliderLabels
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Display the selected label
                    Text(text = sliderLabels[sliderPosition.toInt()])
                }

                // Content on the right side
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //2-add the label horizontal in the center of the screen
                    //3-get value from the weight sensor
                    //4- show the value on the label
                    Text("This is Screen 2", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(16.dp))
                    Weight_Label(weight = 4.554f)
                    Spacer(modifier = Modifier.height(16.dp))
                    Slider_Figures(
                        sliderPosition = sliderPosition,
                        onSliderPositionChange = { newValue ->
                            sliderPosition = newValue
                        },
                        sliderImages = sliderImages
                    )
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Go Back")
                    }
                }
            }
        }
    )
}
