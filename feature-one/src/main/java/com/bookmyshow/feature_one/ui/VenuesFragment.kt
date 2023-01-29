package com.bookmyshow.feature_one.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bookmyshow.feature_one.R

class VenuesFragment : Fragment() {

    companion object {
        fun newInstance() = VenuesFragment()
    }

    private lateinit var viewModel: VenuesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venues, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VenuesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}