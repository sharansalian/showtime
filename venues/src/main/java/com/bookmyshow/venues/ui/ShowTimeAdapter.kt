package com.bookmyshow.venues.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookmyshow.venues.databinding.ItemShowTimeBinding
import com.example.presentation.models.Showtime


class ShowTimeAdapter(val clickListener: ShowTimeListener) :
    ListAdapter<Showtime, ShowTimeAdapter.ViewHolder>(ShowTimeDiffCallback()) {

    class ViewHolder private constructor(val binding: ItemShowTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Showtime, clickListener: ShowTimeListener) {
            binding.showTime = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemShowTimeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }
}

class ShowTimeDiffCallback : DiffUtil.ItemCallback<Showtime>() {

    override fun areItemsTheSame(oldItem: Showtime, newItem: Showtime): Boolean {
        return oldItem.showDateCode == newItem.showDateCode
    }

    override fun areContentsTheSame(oldItem: Showtime, newItem: Showtime): Boolean {
        return oldItem == newItem
    }
}


class ShowTimeListener(val clickListener: (showtime: Showtime) -> Unit) {
    fun onClick(showtime: Showtime) = clickListener(showtime)
}


