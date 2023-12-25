package com.example.starwarschallenge.domain

import com.example.starwarschallenge.domain.model.Movie

sealed class MovieResult {
    data class Success(val movies: List<Movie>) : MovieResult()
    data class Error(val reason: String) : MovieResult()
}

interface MovieRepository {
    suspend fun fetchMovies(): MovieResult
}
