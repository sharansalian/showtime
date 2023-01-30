package com.bookmyshow.seats.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bookmyshow.seats.R

class ShowTimeInfoFragment : Fragment() {

    companion object {
        fun newInstance() = ShowTimeInfoFragment()
    }

    private lateinit var viewModel: ShowTimeInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_time_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowTimeInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}