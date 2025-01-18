package com.example.uas.ui.Map

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun GoogleMapView(
    latitude: Double,
    longitude: Double,
    markerTitle: String,
    zoomLevel: Float
) {
    val context = LocalContext.current
    val mapView = MapView(context).apply {
        onCreate(Bundle())
        getMapAsync(OnMapReadyCallback { googleMap ->
            val location = LatLng(latitude, longitude)
            googleMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .title(markerTitle)
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
            googleMap.uiSettings.isZoomControlsEnabled = true
        })
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier
            .fillMaxSize(),
        update = { it.onResume() }
    )

}