package com.example.starwarschallenge.ui.routes.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.starwarschallenge.ui.routes.root.LocalNavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesView(
    navController: NavController = LocalNavController.current,
    viewModel: MoviesViewModel = koinViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

//    val fetchButtonOnClick: () -> Unit = {
//        println("Fetch!")
//        coroutineScope.launch {
//            println("Fetch!?")
//            viewModel.fetchMovies()
//        }
//    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Title") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (val state = viewModel.state) {
                MoviesState.Initial ->
                    Button(onClick = { viewModel.fetchMovies() }) { Text("Fetch Movies") }

                MoviesState.Loading ->
                    Text("Loading...")

                is MoviesState.Success ->
                    for (m in state.result) {
                        Text(m.title)
                    }

                is MoviesState.Error ->
                    Text(state.reason)
            }
        }
    }
}
