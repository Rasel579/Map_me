package com.teck.data.repositories.datasource

import com.teck.domain.models.Place
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getData(): Flow<List<Place>>
}