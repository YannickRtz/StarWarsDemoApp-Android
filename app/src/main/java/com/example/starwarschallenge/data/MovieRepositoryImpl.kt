package com.example.starwarschallenge.data

import com.example.starwarschallenge.domain.MovieRepository
import com.example.starwarschallenge.domain.MovieResult
import com.example.starwarschallenge.domain.model.Movie
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import java.time.LocalDate

object MovieRepositoryImpl : MovieRepository {
    private const val BASE_URL: String = "https://swapi.dev/api"
    private var MOVIES: String = "$BASE_URL/films/"

    override suspend fun fetchMovies(): MovieResult {
        try {
            val response = client.get(MOVIES)
            val body: String = response.body()
            val parser = Json { ignoreUnknownKeys = true }
            val jsonElement = parser.parseToJsonElement(body)
            val jsonResults = jsonElement.jsonObject["results"]
            val swapiMovies =
                parser.decodeFromJsonElement<List<SWAPIMovie>>(jsonResults!!) // use try catch to handle json and network errors
            val result = swapiMovies.map { s ->
                Movie(
                    title = s.title,
                    id = s.episodeId,
                    date = LocalDate.parse(s.releaseDate)
                )
            }
            return MovieResult.Success(result)
        } catch (e: Exception) {
            return MovieResult.Error(e.toString())
        }
    }

    private var client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        expectSuccess = true
        // JSON Response properties
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}
