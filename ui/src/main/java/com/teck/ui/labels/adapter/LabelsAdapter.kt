package com.teck.ui.labels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teck.domain.models.Place
import com.teck.ui.databinding.ItemLabelMarkupBinding

class LabelsAdapter(private val places: List<Place>, private val click: View.OnClickListener) : RecyclerView.Adapter<LabelsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelsViewHolder =
       LabelsViewHolder(ItemLabelMarkupBinding
           .inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: LabelsViewHolder, position: Int) {
        holder.bind(places[position])
        holder.initListener(click)
    }

    override fun getItemCount(): Int = places.size
}