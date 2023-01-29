package com.bookmyshow.feature_one.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.feature_one.repository.VenueRepositoryImpl
import com.example.domain.usecases.GetVenuesUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.Venue
import com.example.presentation.models.VenuesResponse
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

    init {
        Log.d(TAG, "init: ")

    }


    private val showTimeFilter: MutableLiveData<List<String>> =
        MutableLiveData(listOf())

    fun setShowTimeFilter(list: List<String>) {
        showTimeFilter.value = list
    }

    fun getFilteredVenues(): LiveData<List<String>> = showTimeFilter.switchMap {
        liveData { emit(it) }
    }

    fun getVenues(): LiveData<NetworkStatus<out VenuesResponse>> = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getVenuesUseCase.invoke()) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }
}