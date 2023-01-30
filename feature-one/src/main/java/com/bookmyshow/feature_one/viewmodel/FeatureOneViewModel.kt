package com.bookmyshow.feature_one.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.feature_one.repository.VenueRepositoryImpl
import com.example.domain.usecases.GetVenuesUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.Showtime
import com.example.presentation.models.Venue
import com.example.presentation.models.VenuesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
class FeatureOneViewModel @Inject constructor(
    val venueRepositoryImpl: VenueRepositoryImpl,
    private val getVenuesUseCase: GetVenuesUseCase
) :
    ViewModel() {

    companion object {
        private const val TAG = "FeatureOneViewModel"
    }

    private val _venues: MutableLiveData<List<Venue>> = MutableLiveData()
    val venuess: LiveData<List<Venue>> = _venues

    init {
        Log.d(TAG, "init: ")

        viewModelScope.launch {
            getVenue().collect {
                if (it is NetworkStatus.Success) {
                    _venues.value = it.data?.venues
                    setShowTimeFilter(listOf())
                }
            }
        }

    }


    private val showTimeFilter: MutableLiveData<List<String>> =
        MutableLiveData(listOf())

    fun setShowTimeFilter(list: List<String>) {
        showTimeFilter.value = list
    }

    fun getShowTimeFilter(): LiveData<List<String>> = showTimeFilter.switchMap {
        liveData { emit(it) }
    }

    fun getFilteredVenues(): LiveData<List<Venue?>?> = showTimeFilter.switchMap { filter ->
        liveData {
            if (filter.isNotEmpty()) {
                val mutableVenues: ArrayList<Venue>? =
                    _venues.value?.let { ArrayList(it.map { it.copy() }) }
                val ven: List<Venue?>? = mutableVenues?.map { venue: Venue ->
                    val filteredTime = venue.showtimes.filter { showtime: Showtime ->
                        showtime.type.name in filter
                    }
                    venue.showtimes = filteredTime
                    venue
                }
                ven?.let { emit(it) }
            } else {
                emit(_venues.value)
            }
        }
    }

    fun getVenues(): LiveData<NetworkStatus<out VenuesResponse>> = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getVenuesUseCase.invoke()) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }

    private fun getVenue(): Flow<NetworkStatus<VenuesResponse>> = flow {
        emit(NetworkStatus.Loading())
        when (val result = getVenuesUseCase.invoke()) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }
}