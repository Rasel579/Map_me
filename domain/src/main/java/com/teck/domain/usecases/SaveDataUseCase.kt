package com.teck.domain.usecases

import com.teck.domain.models.Place
import com.teck.domain.repository.Repository

class SaveDataUseCase(private val repository: Repository): InputUseCase<Place> {
    override fun saveData(data: Place) = repository.saveData(data)
}