package com.teck.domain.models

import com.google.android.gms.maps.model.LatLng


data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
)