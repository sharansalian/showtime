package com.bookmyshow.feature_one.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SharedViewModel @Inject constructor() : ViewModel() {
    private val _filterClickEvent: androidx.lifecycle.MutableLiveData<com.bookmyshow.common_ui.utils.Event<Unit>> =
        androidx.lifecycle.MutableLiveData()
    val filterClickEvent: androidx.lifecycle.LiveData<com.bookmyshow.common_ui.utils.Event<Unit>> = _filterClickEvent

    fun onFilterClick() {
        _filterClickEvent.value = com.bookmyshow.common_ui.utils.Event(Unit)
    }
}