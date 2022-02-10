package com.teck.data.repositories

import com.teck.data.repositories.datasource.DataSource
import com.teck.domain.models.Place
import com.teck.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override fun getData(): Flow<List<Place>> = dataSource.getData()
    override suspend fun saveData(place: Place) = dataSource.saveData(place)
    override suspend fun update(place: Place) = dataSource.update(place)
    override suspend fun delete(place: Place) = dataSource.delete(place)
}