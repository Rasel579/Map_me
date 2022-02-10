package com.teck.data.repositories.datasource

import com.teck.domain.models.Place
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getData(): Flow<List<Place>>
    suspend fun saveData(place: Place)
    suspend fun update(place: Place)
    suspend fun delete(place: Place)
}