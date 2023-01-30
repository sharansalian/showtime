package com.bookmyshow.venues.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bookmyshow.venues.databinding.FragmentVenuesBinding
import com.bookmyshow.venues.di.FeatureOneDaggerProvider
import com.bookmyshow.venues.viewmodel.VenuesViewModel
import com.bookmyshow.venues.viewmodel.SharedViewModel
import com.example.showtimefilter.ui.Filter
import com.example.showtimefilter.ui.ShowTimeFilterBottomSheetFragment
import javax.inject.Inject

class VenuesFragment : Fragment() {

    companion object {
        fun newInstance() = VenuesFragment()
        private const val TAG = "VenuesFragment"
    }

    private lateinit var binding: FragmentVenuesBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: VenuesViewModel by viewModels<VenuesViewModel>() { factory }
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>() { factory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVenuesBinding.inflate(layoutInflater)
        return binding.root
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


        binding.apply {


            val adapter = VenueAdapter(
                VenueListener(
                    onVenueClick = {

                    },
                    onShowTime = {
                        Log.d(TAG, "onViewCreated: ${it.showTime}")
                    })
            )
            rvVenues.adapter = adapter
            //

            /*   it.findNavController()
                   .navigate(VenuesFragmentDirections.actionVenuesDestinationToShowTimeInfoDestination())*/

            /*  viewModel.getVenues().observe(viewLifecycleOwner) { result ->
                  when (result) {
                      is NetworkStatus.Error -> {
                          Log.d(TAG, "onViewCreated: ${result.errorMessage}")

                      }
                      is NetworkStatus.Loading -> {
                          Log.d(TAG, "onViewCreated: $result")

                      }
                      is NetworkStatus.Success -> {
                          Log.d(TAG, "onViewCreated: ${result.data}")

                          result.data?.let { response ->
                              adapter.submitList(response.venues)
                          }
                      }
                  }
              }
              */
            viewModel.getFilteredVenues().observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
/*
            viewModel.venuess.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }*/

            viewModel.getShowTimeFilter().observe(viewLifecycleOwner) {
                Log.d(TAG, "getFilteredVenues: $it")
            }

            sharedViewModel.filterClickEvent.observe(viewLifecycleOwner) {
                Log.d(TAG, "filterClickEvent: ")
                it.getContentIfNotHanlded()?.let {
                    ShowTimeFilterBottomSheetFragment.show(
                        childFragmentManager,
                        showTimeFilterBottomSheetListener = object :
                            ShowTimeFilterBottomSheetFragment.ShowTimeFilterBottomSheetListener {
                            override fun onApply(filters: List<Filter>) {
                                filters
                                    .filter { it.isSelected }
                                    .map { it.type }
                                    .let { type -> viewModel.setShowTimeFilter(type) }
                            }

                            override fun onReset() {
                                viewModel.setShowTimeFilter(listOf())
                            }
                        })
                }
            }

        }
    }
}