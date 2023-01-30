package com.bookmyshow.venues.viewmodel

import androidx.lifecycle.*
import com.bookmyshow.common_ui.utils.network.NetworkStatus
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
class VenuesViewModel @Inject constructor(
    private val getVenuesUseCase: GetVenuesUseCase
) :
    ViewModel() {


    private val _venues: MutableLiveData<List<Venue>> = MutableLiveData()
    private val showTimeFilter: MutableLiveData<List<String>> = MutableLiveData(listOf())
    init {
        /**
         * We collect the result on init there is a single event so we can even use first() on the flow,
         * or we can use a Mediator live data inside of using a flow.
         */
        viewModelScope.launch {
            getVenue().collect {
                if (it is NetworkStatus.Success) {
                    _venues.value = it.data?.venues
                    setShowTimeFilter(listOf())
                }
            }
        }

    }

    fun setShowTimeFilter(list: List<String>) {
        showTimeFilter.value = list
    }

    /**
     * Everytime filters are updated the switchMap will get the latest changes,
     * We will create a new copy of venues apply the filters and send it back
     * to the adapter
     */
    fun getFilteredVenues(): LiveData<List<Venue?>?> = showTimeFilter.switchMap { filter ->
        liveData {
            if (filter.isNotEmpty()) {
                val mutableVenues: ArrayList<Venue>? =
                    _venues.value?.let { ArrayList(it.map { it.copy() }) }
                val venues: List<Venue?>? = mutableVenues?.map { venue: Venue ->
                    val filteredTime = venue.showtimes.filter { showtime: Showtime ->
                        showtime.type.name in filter
                    }
                    venue.showtimes = filteredTime
                    venue
                }
                venues?.let { emit(it) }
            } else {
                emit(_venues.value)
            }
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