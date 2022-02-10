package com.teck.domain.usecases

import com.teck.domain.models.Place
import kotlinx.coroutines.flow.Flow

interface InputUseCase<T> {
    suspend fun saveData(data: T)
    suspend fun update(data: T)
    suspend fun delete(data: T)
}