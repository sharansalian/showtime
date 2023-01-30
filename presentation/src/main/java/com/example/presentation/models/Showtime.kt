package com.example.presentation.models

data class Showtime(
    val showDateCode: Long,
    val showTime: String,
    var type: ShowTimeType
)

enum class ShowTimeType {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT,
    UNKNOWN
}

/**
 * A naive function to converting to enum type, an optimized way would be use
 * show date
 */
fun getShowTimeType(showTime: String): ShowTimeType {
    return when (showTime) {
        "09:00am", "10:15am" -> ShowTimeType.MORNING
        "12:15pm", "03:00pm" -> ShowTimeType.AFTERNOON
        "04:00pm" -> ShowTimeType.EVENING
        "09:30pm" -> ShowTimeType.NIGHT
        else -> ShowTimeType.UNKNOWN
    }
}