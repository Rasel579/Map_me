package com.teck.domain.usecases

import com.teck.domain.models.Place
import com.teck.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetDataUseCase(private val repository: Repository) : OutputUseCase<Flow<List<Place>>> {
    override fun getData(): Flow<List<Place>> = repository.getData()
}