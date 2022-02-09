package com.teck.ui.map.libs

import androidx.annotation.IdRes
import com.teck.domain.models.Place

interface Map {
    fun initUiSettings(@IdRes id: Int)
    fun addMarkers(placeData : List<Place>)
    fun initMyLocation()
    fun initListeners(placeData : List<Place>)
}