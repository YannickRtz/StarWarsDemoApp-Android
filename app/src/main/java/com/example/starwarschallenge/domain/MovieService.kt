package com.example.starwarschallenge.domain

// Note: This Service is not strictly necessary for this example
// I include it as an example of a way to structure
// business logic for common use cases
class MovieService(private val movieRepository: MovieRepository) {

    suspend fun fetchMovies(): MovieResult {
        return movieRepository.fetchMovies()
    }

}
