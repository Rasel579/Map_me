package com.teck.ui.labels

import androidx.lifecycle.ViewModel
import com.teck.domain.models.Place
import com.teck.domain.usecases.InputUseCase
import com.teck.domain.usecases.OutputUseCase
import kotlinx.coroutines.flow.Flow

class LabelsViewModel(
    private val getDataUseCase: OutputUseCase<Flow<List<Place>>>,
    private val saveDataUseCase: InputUseCase<Place>
    ) :ViewModel(){
    fun takeData() = getDataUseCase.getData()
    suspend fun updateData(place: Place) = saveDataUseCase.update(place)
    suspend fun deleteData(place: Place) = saveDataUseCase.delete(place)
}