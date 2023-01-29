package com.example.presentation.mappers

import com.example.domain.entities.DomainEntities
import com.example.presentation.models.Showtime
import com.example.presentation.models.Venue
import com.example.presentation.models.VenuesResponse

fun DomainEntities.DomainVenueResponse.map() = VenuesResponse(
    venues = venues.map { it.map() }
)

fun DomainEntities.DomainVenue.map() = Venue(
    name = name,
    showDate = showDate,
    showtimes = showtimes.map { it.map() }
)

fun DomainEntities.DomainShowtime.map() = Showtime(
    showDateCode, showTime
)