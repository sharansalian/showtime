package com.example.presentation.models

data class Venue(
    val name: String,
    val showDate: String,
    val showtimes: List<Showtime>
)