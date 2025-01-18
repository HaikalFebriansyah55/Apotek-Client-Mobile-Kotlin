package com.example.uas.ui.BottomBar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.uas.R
import com.example.uas.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController
) {
    BackHandler(enabled = true){
    }

    NavigationBar (
        modifier = Modifier
            .height(65.dp)
            .fillMaxWidth(),
        contentColor = Color.White,
        containerColor = Color.White,
        windowInsets = WindowInsets.ime
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            bottomClass(
                title = "API",
                icon = painterResource(R.drawable.bottombariconpotter),
                screen = Screen.Potter
            ),
            bottomClass(
                title = "Medicine",
                icon = painterResource(R.drawable.bottombariconmedicine),
                screen = Screen.Medicine
            ),
            bottomClass(
                title = "Graph",
                icon = painterResource(R.drawable.bottombaricongraph),
                screen = Screen.Graph
            ),
            bottomClass(
                title = "User",
                icon = painterResource(R.drawable.bottombariconuser),
                screen = Screen.User
            ),
            bottomClass(
                title = "Information",
                icon = painterResource(R.drawable.bottombariconinformation),
                screen = Screen.Info
            )
        )
        navigationItems.forEachIndexed { index, bottomClass ->
            val selected = currentRoute == bottomClass.screen.route

            NavigationBarItem(
                selected = currentRoute == bottomClass.screen.route,
                onClick = {
                    navController.navigate(bottomClass.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = bottomClass.icon,
                        contentDescription = bottomClass.title,
                        modifier = Modifier
                            .size(25.dp)
                    )
                },
                label = {
                    Text(
                        bottomClass.title,
                        fontSize = 9.sp
                    )
                }
            )
        }
    }
}