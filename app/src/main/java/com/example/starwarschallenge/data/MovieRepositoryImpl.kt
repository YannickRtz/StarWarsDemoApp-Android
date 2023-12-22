package com.example.starwarschallenge.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import com.example.starwarschallenge.domain.MovieRepository
import com.example.starwarschallenge.domain.model.Movie
import io.ktor.client.call.*
import io.ktor.client.request.*

object MovieRepositoryImpl: MovieRepository {
    private const val BASE_URL:String = "https://jsonplaceholder.typicode.com"
    var BLOG_POST:String = "$BASE_URL/posts"

    override suspend fun fetchMovies(): List<Movie> = client.get(BLOG_POST).body()

    // Code Snippet from https://github.com/Rhythamtech/Road-To-JetpackCompose/tree/master/KtorClientExample
    var client = HttpClient(Android) {

        // For Logging
        install(Logging) {
            level = LogLevel.ALL
        }

        // Timeout plugin
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        // JSON Response properties
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }

        // Default request for POST, PUT, DELETE,etc...
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            //add this accept() for accept Json Body or Raw Json as Request Body
            accept(ContentType.Application.Json)
        }

    }
}
