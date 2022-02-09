package com.teck.map_me.di

import com.teck.data.repositories.RepositoryImpl
import com.teck.data.repositories.datasource.DataSource
import com.teck.data.repositories.datasource.MockDataSource
import com.teck.domain.models.Place
import com.teck.domain.repository.Repository
import com.teck.domain.usecases.GetDataUseCase
import com.teck.domain.usecases.OutputUseCase
import com.teck.map_me.R
import com.teck.ui.map.MapFragment
import com.teck.ui.map.MapViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {
    fun dataSourceModules() = module {
        single<DataSource>{ MockDataSource(get(), R.raw.places) }
    }
    fun repositoriesModule() = module {
        single<Repository> { RepositoryImpl(get())}
    }
    fun usecasesModule() = module {
        factory<OutputUseCase<Flow<List<Place>>>> {
            GetDataUseCase(get())
        }
    }

    fun viewModelsModules() = module {
        scope<MapFragment> {
            viewModel{
                MapViewModel(get())
            }
        }
    }

}