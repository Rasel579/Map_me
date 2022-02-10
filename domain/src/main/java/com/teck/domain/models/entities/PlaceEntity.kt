package com.teck.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val latLng: String,
    val address: String,
    val rating: Float
)