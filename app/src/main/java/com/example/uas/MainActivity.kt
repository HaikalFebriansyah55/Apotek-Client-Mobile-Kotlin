package com.example.uas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.uas.firebase.LoginViewModel
import com.example.uas.navigation.Screen
import com.example.uas.retrofit.ObatViewModel
import com.example.uas.ui.BottomBar.BottomBar
import com.example.uas.ui.BottomBar.HelperBar
import com.example.uas.ui.Map.Map
import com.example.uas.ui.presentation.Graph
import com.example.uas.ui.presentation.Info
import com.example.uas.ui.presentation.Login
import com.example.uas.ui.presentation.Medicine
import com.example.uas.ui.presentation.Potter
import com.example.uas.ui.presentation.Register
import com.example.uas.ui.presentation.User
import com.example.uas.ui.theme.UASTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        // Variable to control splash screen duration
        var isSplashScreenVisible = true
        splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    delay(5000) // 5 seconds delay
                    isSplashScreenVisible = false
                }
            }

            UASTheme {
                val navController: NavHostController = rememberNavController()
                val loginViewModel : LoginViewModel = hiltViewModel()
                val state = loginViewModel.state.collectAsState(initial = null)
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val viewModel: ObatViewModel = viewModel()
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(visible = currentRoute.HelperBar()) {
                            BottomBar(navController)
                        }
                    },
                    topBar = {
                        AnimatedVisibility(visible = currentRoute.HelperBar()) {
                           TopAppBar(
                               title = {
                                   when (currentRoute){
                                       Screen.Potter.route -> Text(
                                           text = "API PUBLIC",
                                           fontSize = 14.sp
                                       )
                                       Screen.Medicine.route -> Text(
                                           text = "List Obat Kesehatan",
                                           fontSize = 14.sp
                                       )
                                       Screen.Graph.route -> Text(
                                           text = "Grafik Harga Obat dan Rekap Obat",
                                           fontSize = 14.sp
                                       )
                                       Screen.User.route -> Text(
                                           text = "User Info",
                                           fontSize = 14.sp
                                       )
                                       Screen.Info.route -> Text(
                                           text = "Tentang Aplikasi",
                                           fontSize = 14.sp
                                       )
                                   }
                               },
                               modifier = Modifier
                                   .shadow(4.dp),
                               actions = {
                                   IconButton(onClick = {
                                       loginViewModel.logoutUser { success ->
                                           if (success) {
                                               Toast.makeText(applicationContext,"Logout Berhasil !", Toast.LENGTH_SHORT).show()
                                               navController.navigate(Screen.Login.route){
                                                   popUpTo(Screen.Login.route){
                                                       inclusive = true
                                                   }
                                               }
                                           } else {
                                               Toast.makeText(applicationContext, "Logout error", Toast.LENGTH_SHORT)
                                                   .show()
                                           }
                                       }
                                   }) {
                                       Icon(
                                           imageVector = Icons.Default.ExitToApp,
                                           contentDescription = ""
                                       )
                                   }
                               }

                           )
                        }
                    },
                    floatingActionButton = {
                        AnimatedVisibility(visible = currentRoute.HelperBar() || currentRoute == Screen.Map.route) {
                            FloatingActionButton(onClick = {
                                if (currentRoute == Screen.Map.route){
                                    navController.popBackStack()
                                } else {
                                    navController.navigate(Screen.Map.route)
                                }
                            }) {
                                if (currentRoute == Screen.Map.route){
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = ""
                                    )
                                } else {

                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                        modifier = Modifier
                            .padding(paddingValues = it)
                    ) {
                        composable(Screen.Login.route) {
                            Login(navController)
                        }
                        composable(Screen.Register.route) {
                            Register(navController)
                        }
                        composable(Screen.Potter.route) {
                            Potter()
                        }
                        composable(Screen.Medicine.route) {
                            Medicine()
                        }
                        composable(Screen.Graph.route) {
                            val obatList by viewModel.obatList.observeAsState(emptyList())
                            Graph(obatList = obatList)
                        }
                        composable(Screen.Map.route){
                            Map()
                        }
                        composable(Screen.User.route){
                            User()
                        }
                        composable(Screen.Info.route){
                            Info()
                        }
                    }
                }
            }
        }
    }
}

