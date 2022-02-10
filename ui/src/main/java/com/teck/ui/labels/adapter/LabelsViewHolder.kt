package com.teck.ui.labels.adapter

import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.teck.domain.models.Place
import com.teck.ui.databinding.ItemLabelMarkupBinding

class LabelsViewHolder(
    private val binding: ItemLabelMarkupBinding,
    private val click: (Place) -> Unit)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(place: Place) {
        with(binding) {
            titleEditText.setText(place.name)
            adressEditText.setText(place.address)
            ratingEditText.setText("${place.rating}")
            saveBtn.setOnClickListener {
                click.invoke(toPlace(place))
            }
        }
    }

    private fun toPlace(place: Place) = Place(
        id = place.id,
        name = binding.titleEditText.text.toString(),
        latLng = place.latLng,
        address = binding.adressEditText.text.toString(),
        rating = place.rating
    )

}