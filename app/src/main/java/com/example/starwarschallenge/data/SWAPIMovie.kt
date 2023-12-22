package com.example.starwarschallenge.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SWAPIMovie(
    @SerialName(value = "title")
    val title: String,

    @SerialName(value = "episode_id")
    val episodeId: String,

    @SerialName(value = "opening_crawl")
    val userId: String,

    @SerialName(value = "director")
    val director: String,

    @SerialName(value = "producer")
    val producer: String,

    @SerialName(value = "release_date")
    val releaseDate: String

    // etc...
    // Note: Leaving out more complex data types because of time constraints
)
