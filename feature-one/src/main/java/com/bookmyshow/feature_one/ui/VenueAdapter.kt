package com.bookmyshow.feature_one.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookmyshow.feature_one.databinding.ItemVenueBinding
import com.example.presentation.models.Showtime
import com.example.presentation.models.Venue


class VenueAdapter(val clickListener: VenueListener) :
    ListAdapter<Venue, VenueAdapter.ViewHolder>(VenueDiffCallback()) {

    class ViewHolder private constructor(
        val binding: ItemVenueBinding,
        val onShowTime: (showtime: Showtime) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private var showTimeAdapter: ShowTimeAdapter? = null

        init {
            showTimeAdapter = ShowTimeAdapter(ShowTimeListener {
                onShowTime(it)
            })
            binding.apply {
                rvShowTime.adapter = showTimeAdapter
            }

        }

        fun bind(item: Venue, clickListener: VenueListener) {
            binding.venue = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            showTimeAdapter?.submitList(item.showtimes)
        }

        companion object {
            fun from(parent: ViewGroup, onShowTime: (showtime: Showtime) -> Unit): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemVenueBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onShowTime = onShowTime)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onShowTime = {
            clickListener.onShowTime(it)
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }
}

class VenueDiffCallback : DiffUtil.ItemCallback<Venue>() {

    override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return false
    }
}


class VenueListener(
    val onVenueClick: (venue: Venue) -> Unit,
    val onShowTime: (showtime: Showtime) -> Unit
) {
    fun onClick(venue: Venue) = onVenueClick(venue)
    fun onShowTimeClick(showtime: Showtime) = onShowTime(showtime)
}
