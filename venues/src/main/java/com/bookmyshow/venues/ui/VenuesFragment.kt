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
import androidx.navigation.fragment.findNavController
import com.bookmyshow.venues.databinding.FragmentVenuesBinding
import com.bookmyshow.venues.di.VenueDaggerProvider
import com.bookmyshow.venues.viewmodel.VenuesViewModel
import com.bookmyshow.venues.viewmodel.SharedViewModel
import com.example.presentation.models.Filter
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
        VenueDaggerProvider.component.inject(this)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {

            val adapter = VenueAdapter(
                VenueListener(
                    onVenueClick = {

                    },
                    onShowTime = {
                        findNavController().navigate(VenuesFragmentDirections.actionVenuesDestinationToShowTimeInfoDestination())
                    })
            )
            rvVenues.adapter = adapter

            viewModel.getFilteredVenues().observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            sharedViewModel.filterClickEvent.observe(viewLifecycleOwner) {
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