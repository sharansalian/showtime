package com.bookmyshow.feature_one.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bookmyshow.feature_one.R
import com.bookmyshow.feature_one.di.FeatureOneDaggerProvider
import com.bookmyshow.feature_one.viewmodel.FeatureOneViewModel
import javax.inject.Inject

class VenuesFragment : Fragment() {

    companion object {
        fun newInstance() = VenuesFragment()
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
            viewModel.fetch()
         /*   it.findNavController()
                .navigate(VenuesFragmentDirections.actionVenuesDestinationToShowTimeInfoDestination())*/
        }
    }

}