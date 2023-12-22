package com.example.starwarschallenge.domain

import com.example.starwarschallenge.domain.model.Movie

interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}
