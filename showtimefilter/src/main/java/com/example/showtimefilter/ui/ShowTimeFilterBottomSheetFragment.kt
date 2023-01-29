package com.example.showtimefilter.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.venuefilter.R

class ShowTimeFilterBottomSheetFragment : Fragment() {

    companion object {
        fun newInstance() = ShowTimeFilterBottomSheetFragment()
    }

    private lateinit var viewModel: ShowTimeFilterBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_time_filter_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowTimeFilterBottomSheetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}