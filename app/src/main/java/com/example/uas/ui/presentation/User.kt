package com.example.uas.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.uas.R // Ganti dengan package aplikasi Anda

@Composable
fun User() {
    // Add vertical scrolling
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState), // Enable scrolling
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DeveloperCard(
            imageRes = R.drawable.ale, // Ganti dengan nama file di drawable
            name = "Muhammad Nalendra Zaradiva",
            id = "152022112"
        )
        DeveloperCard(
            imageRes = R.drawable.haikal, // Ganti dengan nama file di drawable
            name = "Muhammad P Haikal Febriansyah",
            id = "152022088"
        )
    }
}

@Composable
fun DeveloperCard(imageRes: Int, name: String, id: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Display developer image with fixed size and more rounded corners
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp) // Fixed size for the image
                .clip(CircleShape) // Round shape
                .padding(0.dp) // Remove padding to keep the circle
        )

        // Display developer name
        Text(text = name)

        // Display developer ID
        Text(text = id)
    }
}
