package com.example.starwarschallenge.domain.model

import java.time.LocalDate

data class Movie(
    val title: String,
    val id: Int,
    val date: LocalDate,
)
