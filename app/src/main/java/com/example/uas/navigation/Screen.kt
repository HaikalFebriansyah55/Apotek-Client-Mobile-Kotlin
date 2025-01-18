package com.example.uas.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("Login")
    data object Register : Screen("Register")
    data object Medicine : Screen("Medicine")
    data object Graph : Screen("Graph")
    data object Potter : Screen("Potter")
    data object Map : Screen("Map")
    data object User : Screen("User")
    data object Info : Screen("Info")

}