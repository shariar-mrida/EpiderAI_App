package com.epiderai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epiderai.app.ui.screens.LoginScreen
import com.epiderai.app.ui.screens.MainScreen
import com.epiderai.app.ui.theme.EpiderAiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpiderAiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { 
                           LoginScreen(onLoginSuccess = { 
                               navController.navigate("main") {
                                   popUpTo("login") { inclusive = true }
                               }
                           })
                        }
                        composable("main") {
                           MainScreen(onLogout = {
                               navController.navigate("login") {
                                   popUpTo("main") { inclusive = true }
                               }
                           })
                        }
                    }
                }
            }
        }
    }
}
