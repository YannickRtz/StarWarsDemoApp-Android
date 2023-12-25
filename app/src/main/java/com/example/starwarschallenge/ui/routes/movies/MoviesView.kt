package com.example.starwarschallenge.ui.routes.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarschallenge.ui.routes.root.LocalNavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesView(
    navController: NavController = LocalNavController.current,
    viewModel: MoviesViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Movies") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = if (state !is MoviesState.Success) Arrangement.Center else Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (val state = state) {
                MoviesState.Initial -> {
                    Button(
                        onClick = { viewModel.fetchMovies() }
                    ) {
                        Text("Fetch Movies")
                    }
                }

                MoviesState.Loading -> {
                    CircularProgressIndicator()
                    Text("Getting Movies...")
                }

                is MoviesState.Success ->
                    for (m in state.result) {
                        ListItem(
                            headlineContent = { Text(m.title) },
                            leadingContent = { Text(m.date.year.toString()) },
                            tonalElevation = 4.dp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }

                is MoviesState.Error -> {
                    Text(state.reason)
                    Button(
                        modifier = Modifier.padding(),
                        onClick = { viewModel.fetchMovies() },
                    ) {
                        Text("Try again")
                    }
                }
            }
        }
    }
}
