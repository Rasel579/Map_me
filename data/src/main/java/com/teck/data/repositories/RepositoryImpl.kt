package com.teck.data.repositories

import com.teck.data.repositories.datasource.DataSource
import com.teck.domain.models.Place
import com.teck.domain.repository.Repository

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override fun getData(): List<Place> = dataSource.getData()
}