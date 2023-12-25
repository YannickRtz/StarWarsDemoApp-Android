package com.example.starwarschallenge.ui.routes.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarschallenge.domain.MovieService
import com.example.starwarschallenge.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class MoviesState {
    data object Initial : MoviesState()
    data object Loading : MoviesState()
    data class Success(val result: List<Movie>) : MoviesState()
    data class Error(val reason: String) : MoviesState()
}

class MoviesViewModel(private val movieService: MovieService) : ViewModel() {
    var state = MutableStateFlow<MoviesState>(MoviesState.Initial)

    fun fetchMovies() {
        println("Hey")
        state.value = MoviesState.Loading
        try {
            viewModelScope.launch {
                val result = movieService.fetchMovies()
                state.value = MoviesState.Success(result)
            }
        } catch (e: Exception) {
            state.value = MoviesState.Error(e.toString())
        }
    }
}
