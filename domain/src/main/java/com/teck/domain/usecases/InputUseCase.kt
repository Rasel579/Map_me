package com.teck.domain.usecases

import com.teck.domain.models.Place
import kotlinx.coroutines.flow.Flow

interface InputUseCase<T> {
    fun saveData(data: T)
}