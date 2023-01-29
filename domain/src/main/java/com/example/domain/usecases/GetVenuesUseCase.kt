package com.example.domain.usecases

import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities
import com.example.domain.repository.VenueRepository
import javax.inject.Inject

class GetVenuesUseCase @Inject constructor(private val venueRepository: VenueRepository) {

    suspend operator fun invoke(): NetworkStatus<DomainEntities.DomainVenueResponse> =
        venueRepository.getVenues()
}