package com.bookmyshow.venues.repository

import com.example.domain.entities.DomainEntities
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
interface ShowTimesAPI {

    @GET("/movie_showtimes")
    suspend fun getShowTimes(): Response<DomainEntities.DomainVenueResponse>
}