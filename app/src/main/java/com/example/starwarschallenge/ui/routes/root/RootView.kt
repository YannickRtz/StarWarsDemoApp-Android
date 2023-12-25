package com.example.starwarschallenge.ui.routes.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.starwarschallenge.ui.routes.lightsabers.LightsabersView
import com.example.starwarschallenge.ui.routes.movies.MoviesView

val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }

@Composable
fun RootView(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(modifier = Modifier.fillMaxWidth()) {
                    StarWarsTabs.bottomNavigationItems().forEach { item ->
                        val isSelectedRoute =
                            currentDestination?.hierarchy?.any { it.route == item.route }
                        NavigationBarItem(
                            selected = isSelectedRoute == true,
                            modifier = Modifier,
                            label = { Text(item.label) },
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.id)
                                }
                            },
                            icon = {
                                Icon(imageVector = item.icon, contentDescription = item.label)
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                modifier = modifier.padding(paddingValues = paddingValues),
                navController = navController,
                startDestination = StarWarsTabs.MoviesTab().route
            ) {
                StarWarsTabs.bottomNavigationItems().forEach { item ->
                    composable(item.route) {
                        when (item) {
                            is StarWarsTabs.MoviesTab -> MoviesView()
                            is StarWarsTabs.LightsabersTab -> LightsabersView()
                        }
                    }
                }
            }
        }
    }
}
