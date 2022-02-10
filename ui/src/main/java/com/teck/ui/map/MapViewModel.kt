package com.teck.ui.map

import androidx.lifecycle.ViewModel
import com.teck.domain.models.Place
import com.teck.domain.usecases.InputUseCase
import com.teck.domain.usecases.OutputUseCase
import kotlinx.coroutines.flow.Flow

class MapViewModel(
     private val useCase: OutputUseCase<Flow<List<Place>>>,
     private val saveDataUseCase: InputUseCase<Place>) : ViewModel() {
     fun takeData() = useCase.getData()
     suspend fun saveData(place: Place) = saveDataUseCase.saveData(place)
}