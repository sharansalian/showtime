package com.bookmyshow.feature_one.repository

import com.bookmyshow.common_ui.data.VenuesResponse
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.common_ui.utils.network.safeApiCall
import com.bookmyshow.core.NetworkProvider
import com.example.domain.entities.DomainEntities
import com.example.domain.repository.VenueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
class VenueRepositoryImpl @Inject constructor(
    private val networkProvider: NetworkProvider
): VenueRepository {

    private val api: ShowTimesAPI
        get() = networkProvider.getApi(
            apiClass = ShowTimesAPI::class.java,
            baseUrl = "https://demo2782755.mockable.io"
        )

    suspend fun fetchVenues(): NetworkStatus<DomainEntities.DomainVenueResponse> {
        return safeApiCall { api.getShowTimes() }
    }

    override suspend fun getVenues(): NetworkStatus<DomainEntities.DomainVenueResponse> {
        return withContext(Dispatchers.IO) { fetchVenues() }
    }
}