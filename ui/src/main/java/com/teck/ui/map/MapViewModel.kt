package com.teck.ui.map

import androidx.lifecycle.ViewModel
import com.teck.domain.models.Place
import com.teck.domain.usecases.OutputUseCase
import kotlinx.coroutines.flow.Flow

class MapViewModel(private val useCase: OutputUseCase<Flow<List<Place>>>) : ViewModel() {
     fun takeData() = useCase.getData()
}