package com.teck.data.repositories.datasource

import com.teck.domain.models.Place

interface DataSource {
    fun getData(): List<Place>
}