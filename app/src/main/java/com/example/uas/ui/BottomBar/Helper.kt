package com.example.uas.ui.BottomBar

import com.example.uas.navigation.Screen

fun String?.HelperBar(): Boolean {
    return this in setOf(
        Screen.Potter.route,
        Screen.Medicine.route,
        Screen.Graph.route,
        Screen.User.route,
        Screen.Info.route
    )
}