package com.teck.domain.repository

import com.teck.domain.models.Place
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getData() : Flow<List<Place>>
}