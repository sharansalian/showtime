package com.bookmyshow.feature_one.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bookmyshow.common_ui.data.VenuesResponse
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.feature_one.repository.VenueRepositoryImpl
import com.example.domain.usecases.GetVenuesUseCase
import kotlinx.coroutines.Dispatchers
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

    init {
        Log.d(TAG, "init: ")

    }

    fun getVenues() = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getVenuesUseCase.invoke()) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }
}