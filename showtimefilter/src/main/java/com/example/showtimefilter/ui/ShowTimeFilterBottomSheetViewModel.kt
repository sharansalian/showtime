package com.example.showtimefilter.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ShowTimeFilterBottomSheetViewModel @Inject constructor() : ViewModel() {
    // TODO: Implement the ViewModel

    companion object {
        private const val TAG = "ShowTimeFilterBottomShe"
    }

    init {
        Log.d(TAG, "init: ")

    }

    val filters: MutableLiveData<List<Filter>> =
        MutableLiveData(
            listOf(
                Filter(1, false, "Morning", "1"),
                Filter(2, false, "Afternoon", "2"),
                Filter(3, false, "Evening", "3"),
                Filter(4, false, "Night", "4"),
            )
        )


    fun reset() {
        filters.value = filters.value?.map {
            it.isSelected = false
            it
        }
    }


}