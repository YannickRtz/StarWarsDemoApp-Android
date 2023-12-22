package com.example.starwarschallenge.data

import com.example.starwarschallenge.domain.MovieRepository
import com.example.starwarschallenge.domain.model.Movie
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

object MovieRepositoryImpl : MovieRepository {
    private const val BASE_URL: String = "https://swapi.dev/api"
    private var MOVIES: String = "$BASE_URL/films/"

    override suspend fun fetchMovies(): List<Movie> {
        val response = client.get(MOVIES)
        val body: String = response.body()
        val jsonElement = Json.parseToJsonElement(body)
        val jsonResults = jsonElement.jsonObject["results"]
        val swapiMovies =
            Json.decodeFromJsonElement<List<SWAPIMovie>>(jsonResults!!) // use try catch to handle json and network errors
    }

    // Code Snippet from https://github.com/Rhythamtech/Road-To-JetpackCompose/tree/master/KtorClientExample
    @OptIn(ExperimentalSerializationApi::class)
    var client = HttpClient(Android) {

        // For Logging
        install(Logging) {
            level = LogLevel.ALL
        }

        expectSuccess = true

//        // Timeout plugin
//        install(HttpTimeout) {
//            requestTimeoutMillis = 15000L
//            connectTimeoutMillis = 15000L
//            socketTimeoutMillis = 15000L
//        }
//
//        // JSON Response properties
//        install(ContentNegotiation) {
//            json(
//                Json {
//                    ignoreUnknownKeys = true
//                    prettyPrint = true
//                    isLenient = true
//                    explicitNulls = false
//                }
//            )
//        }
//
//        // Default request for POST, PUT, DELETE,etc...
//        install(DefaultRequest) {
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//            //add this accept() for accept Json Body or Raw Json as Request Body
//            accept(ContentType.Application.Json)
//        }

    }
}
