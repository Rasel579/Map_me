package com.teck.ui.labels

import androidx.lifecycle.ViewModel
import com.teck.domain.models.Place
import com.teck.domain.usecases.OutputUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LabelsViewModel(private val getDataUseCase: OutputUseCase<Flow<List<Place>>>) :ViewModel(){
    fun takeData() = getDataUseCase.getData()
}