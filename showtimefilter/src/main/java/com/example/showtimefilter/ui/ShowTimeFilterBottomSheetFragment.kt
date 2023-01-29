package com.example.showtimefilter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.showtimefilter.di.ShowTimeDaggerProvider
import com.example.venuefilter.databinding.FragmentShowTimeFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class ShowTimeFilterBottomSheetFragment(private val showTimeFilterBottomSheetListener: ShowTimeFilterBottomSheetListener) :
    BottomSheetDialogFragment() {

    companion object {

        private const val TAG = "ShowTimeFilterBottomShe"
        fun show(
            fragmentManager: FragmentManager,
            showTimeFilterBottomSheetListener: ShowTimeFilterBottomSheetListener
        ) {
            val fragment = ShowTimeFilterBottomSheetFragment(showTimeFilterBottomSheetListener)
            fragment.show(
                fragmentManager,
                TAG
            )
        }
    }

    private lateinit var binding: FragmentShowTimeFilterBottomSheetBinding


    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: ShowTimeFilterBottomSheetViewModel by activityViewModels() { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowTimeFilterBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ShowTimeDaggerProvider.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val adapter = ShowTimeFilterAdapter(mutableListOf()) {

            }
            rvFilters.adapter = adapter

            viewModel.filters.observe(viewLifecycleOwner) {
                adapter.filters.clear()
                it?.let { it1 -> adapter.filters.addAll(it1) }
                binding.rvFilters.post {
                    adapter.notifyDataSetChanged()
                }
            }

            btnApply.setOnClickListener {
                showTimeFilterBottomSheetListener.onApply(adapter.filters)
                dismiss()
            }

            btnReset.setOnClickListener {
                viewModel.reset()
                showTimeFilterBottomSheetListener.onReset()
                dismiss()
            }
        }

    }

    interface ShowTimeFilterBottomSheetListener {
        fun onApply(filters: List<Filter>)
        fun onReset()
    }

}

//TODO: Move to presentation
data class Filter(var id: Int, var isSelected: Boolean = false, val label: String, val type: String)
