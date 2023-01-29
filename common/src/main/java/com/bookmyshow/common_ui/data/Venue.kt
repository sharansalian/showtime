package com.bookmyshow.common_ui.data

data class Venue(
    val name: String,
    val showDate: String,
    val showtimes: List<Showtime>
)