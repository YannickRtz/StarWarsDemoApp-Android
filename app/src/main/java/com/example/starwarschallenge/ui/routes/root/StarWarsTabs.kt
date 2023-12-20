package com.example.starwarschallenge.ui.routes.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class StarWarsTabs(
    val label: String,
    val icon: ImageVector,
    val route: String,
) {
    class MoviesTab: StarWarsTabs(
        label = "Movies",
        icon = Icons.Filled.Home,
        route = "movies"
    )
    class LightsabersTab: StarWarsTabs(
        label = "Lightsabers",
        icon = Icons.Filled.Star,
        route = "lightsabers"
    )

    companion object {
        fun bottomNavigationItems() : List<StarWarsTabs> {
            return listOf(
                MoviesTab(), LightsabersTab()
            )
        }
    }
}
