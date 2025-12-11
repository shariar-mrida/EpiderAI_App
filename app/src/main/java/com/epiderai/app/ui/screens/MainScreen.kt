package com.epiderai.app.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("EpiderAI") },
                navigationIcon = {
                    if (currentDestination == "profile") {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    if (currentDestination == "profile") {
                        IconButton(onClick = onLogout) {
                            Icon(Icons.Outlined.Logout, contentDescription = "Logout")
                        }
                    } else {
                        IconButton(onClick = { navController.navigate("profile") }) {
                            Icon(Icons.Outlined.AccountCircle, contentDescription = "Profile")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.DocumentScanner, contentDescription = "Analysis") },
                    label = { Text("Scan") },
                    selected = currentDestination == "analysis",
                    onClick = {
                        navController.navigate("analysis") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.MedicalServices, contentDescription = "Doctors") },
                    label = { Text("Doctors") },
                    selected = currentDestination == "booking",
                    onClick = {
                        navController.navigate("booking") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Checklist, contentDescription = "Tasks") },
                    label = { Text("To-Do") },
                    selected = currentDestination == "todo",
                    onClick = {
                        navController.navigate("todo") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.ShoppingBag, contentDescription = "Shop") },
                    label = { Text("Shop") },
                    selected = currentDestination == "shop",
                    onClick = {
                        navController.navigate("shop") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "analysis",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("analysis") { AnalysisScreen() }
            composable("booking") { BookingScreen() }
            composable("todo") { ToDoScreen() }
            composable("shop") { ShopScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
