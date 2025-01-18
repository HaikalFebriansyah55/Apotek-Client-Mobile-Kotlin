package com.example.uas.ui.Map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Map() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        GoogleMapView(
            latitude = -6.898011527840505, 107.63584218166726,
            markerTitle = "Itenas",
            zoomLevel = 15f
        )
    }
}