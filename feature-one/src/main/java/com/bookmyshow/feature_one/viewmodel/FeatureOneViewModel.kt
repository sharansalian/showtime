package com.bookmyshow.feature_one.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmyshow.common_ui.data.VenuesResponse
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.feature_one.repository.ShowTimesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
class FeatureOneViewModel @Inject constructor(val showTimesRepository: ShowTimesRepository) :
    ViewModel() {

    companion object {
        private const val TAG = "FeatureOneViewModel"
    }

        init {
            Log.d(TAG, "init: ")

        }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            val res: NetworkStatus<VenuesResponse> = showTimesRepository.getVenues()

            val v = res.data?.venues

            v

            Log.d(TAG, "${res.data?.venues.toString()} ")
            Log.d(TAG, "${res.errorMessage} ")

            val x = 1
        }
    }
}