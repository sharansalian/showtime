package com.bookmyshow.feature_one.repository

import com.bookmyshow.common_ui.data.VenuesResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
interface ShowTimesAPI {

    @GET("/movie_showtimes")
    suspend fun getShowTimes(): Response<VenuesResponse>
}