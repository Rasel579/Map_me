package com.teck.domain.usecases

import com.teck.domain.models.Place
import com.teck.domain.repository.Repository

class SaveDataUseCase(private val repository: Repository): InputUseCase<Place> {
    override suspend fun saveData(data: Place) = repository.saveData(data)
    override suspend fun update(data: Place) = repository.update(data)
    override suspend fun delete(data: Place) = repository.delete(data)
}