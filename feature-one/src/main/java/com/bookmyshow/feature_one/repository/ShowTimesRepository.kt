package com.bookmyshow.feature_one.repository

import com.bookmyshow.common_ui.data.VenuesResponse
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.common_ui.utils.network.safeApiCall
import com.bookmyshow.core.NetworkProvider
import javax.inject.Inject

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
class ShowTimesRepository @Inject constructor(
    private val networkProvider: NetworkProvider
) {

    private val api: ShowTimesAPI
        get() = networkProvider.getApi(
            apiClass = ShowTimesAPI::class.java,
            baseUrl = "https://demo2782755.mockable.io"
        )

    suspend fun getVenues(): NetworkStatus<VenuesResponse> {
        return safeApiCall { api.getShowTimes() }
    }
}