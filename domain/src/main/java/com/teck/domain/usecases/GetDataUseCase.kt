package com.teck.domain.usecases

import com.teck.domain.models.Place
import com.teck.domain.repository.Repository

class GetDataUseCase(private val repository: Repository) : OutputUseCase<List<Place>> {
    override fun getData(): List<Place> = repository.getData()
}