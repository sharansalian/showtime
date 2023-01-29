package com.bookmyshow.feature_one.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bookmyshow.common_ui.utils.network.NetworkStatus
import com.bookmyshow.feature_one.R
import com.bookmyshow.feature_one.di.FeatureOneDaggerProvider
import com.bookmyshow.feature_one.viewmodel.FeatureOneViewModel
import javax.inject.Inject

class VenuesFragment : Fragment() {

    companion object {
        fun newInstance() = VenuesFragment()
        private const val TAG = "VenuesFragment"
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: FeatureOneViewModel by viewModels<FeatureOneViewModel>() { factory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venues, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureOneDaggerProvider.component.inject(this)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            viewModel.getVenues().observe(viewLifecycleOwner) { result ->
                when(result) {
                    is NetworkStatus.Error -> {
                        Log.d(TAG, "onViewCreated: ${result.errorMessage}")

                    }
                    is NetworkStatus.Loading -> {
                        Log.d(TAG, "onViewCreated: $result")

                    }
                    is NetworkStatus.Success -> {
                        Log.d(TAG, "onViewCreated: ${result.data}")

                    }
                }
            }
         /*   it.findNavController()
                .navigate(VenuesFragmentDirections.actionVenuesDestinationToShowTimeInfoDestination())*/
        }
    }

}