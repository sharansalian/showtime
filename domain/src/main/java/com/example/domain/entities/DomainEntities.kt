package com.example.domain.entities

sealed class DomainEntities {

    /**
     * Data classes for Matches
     */
    data class DomainVenueResponse(
        var venues: List<DomainVenue> = ArrayList()
    ) : DomainEntities()


    data class DomainVenue(
        val name: String,
        val showDate: String,
        val showtimes: List<DomainShowtime>,
    ) : DomainEntities()


    data class DomainShowtime(
        val showDateCode: Long,
        val showTime: String
    )

}