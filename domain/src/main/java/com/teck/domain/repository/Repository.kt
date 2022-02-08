package com.teck.domain.repository

import com.teck.domain.models.Place

interface Repository {
    fun getData() : List<Place>
}