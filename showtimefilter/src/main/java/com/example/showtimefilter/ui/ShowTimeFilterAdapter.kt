package com.example.showtimefilter.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.models.Filter
import com.example.presentation.models.ShowTimeType
import com.example.venuefilter.R


class ShowTimeFilterAdapter(val filters: MutableList<Filter>, val onApply: (Filter) -> Unit) :
    RecyclerView.Adapter<ShowTimeFilterAdapter.ViewHolder>() {

    private val TAG = "TFAdapter"


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cb: CheckBox = ItemView.findViewById(R.id.cb)
        val title: TextView = ItemView.findViewById(R.id.txt_title)
        val iv: ImageView = ItemView.findViewById(R.id.iv_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = filters[position]
        holder.cb.setOnCheckedChangeListener(null)

        holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->
            onApply(filter)
            filters.get(holder.adapterPosition).isSelected = isChecked

        }
        holder.cb.isChecked = filter.isSelected
        holder.title.text = filter.label
        when (filter.type) {
            ShowTimeType.MORNING.name -> holder.iv.background =
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_morning)
            ShowTimeType.AFTERNOON.name -> holder.iv.background =
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_afternoon)
            ShowTimeType.EVENING.name -> holder.iv.background =
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_evening)
            ShowTimeType.NIGHT.name -> holder.iv.background =
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_night)
        }
    }
}