package com.teck.ui.map

import androidx.lifecycle.ViewModel
import com.teck.domain.models.Place
import com.teck.domain.usecases.OutputUseCase

class MapViewModel(private val useCase: OutputUseCase<List<Place>>): ViewModel() {
    fun getData(): List<Place> = useCase.getData()
}