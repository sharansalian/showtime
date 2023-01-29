package com.example.domain.repository

import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities

interface VenueRepository {

    suspend fun getVenues(): NetworkStatus<DomainEntities.DomainVenueResponse>

}