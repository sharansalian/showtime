package com.example.showtimefilter.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.models.ShowTimeType
import javax.inject.Inject

class ShowTimeFilterBottomSheetViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val TAG = "ShowTimeFilterBottomShe"
    }

    init {
        Log.d(TAG, "init: ")
    }

    val filters: MutableLiveData<List<Filter>> =
        MutableLiveData(
            listOf(
                Filter(1, false, "Morning", ShowTimeType.MORNING.name),
                Filter(2, false, "Afternoon", ShowTimeType.AFTERNOON.name),
                Filter(3, false, "Evening", ShowTimeType.EVENING.name),
                Filter(4, false, "Night", ShowTimeType.NIGHT.name),
            )
        )

    fun reset() {
        filters.value = filters.value?.map {
            it.isSelected = false
            it
        }
    }


}