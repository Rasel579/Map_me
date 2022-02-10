package com.teck.domain.repository

import com.teck.domain.models.Place
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getData() : Flow<List<Place>>
    suspend fun saveData(place: Place)
    suspend fun update(place: Place)
    suspend fun delete(place: Place)
}