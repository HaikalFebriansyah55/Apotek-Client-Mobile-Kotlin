package com.example.uas.ui.BottomBar

import androidx.compose.ui.graphics.painter.Painter
import com.example.uas.navigation.Screen

data class bottomClass(
    val title: String,
    val icon: Painter,
    val screen: Screen
)
