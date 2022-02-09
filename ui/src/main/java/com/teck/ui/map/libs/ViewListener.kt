package com.teck.ui.map.libs

import com.teck.domain.models.Place

interface ViewListener {
    fun  saveData(place: Place)
}