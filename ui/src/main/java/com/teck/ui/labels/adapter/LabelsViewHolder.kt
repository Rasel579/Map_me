package com.teck.ui.labels.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.teck.domain.models.Place
import com.teck.ui.databinding.ItemLabelMarkupBinding

class LabelsViewHolder(private val binding: ItemLabelMarkupBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(place: Place) {
        with(binding) {
            titleEditText.setText(place.name)
            adressEditText.setText(place.address)
            ratingEditText.setText(" Rating: ${place.rating}")
            titleEditText.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus){
                    Log.e("focus", "no focus")
                }
            }
        }
    }

    fun initListener(click: View.OnClickListener) {
           binding.root.setOnClickListener(click)
    }
}