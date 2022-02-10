package com.teck.map_me.di

import com.teck.data.repositories.RepositoryImpl
import com.teck.data.repositories.datasource.DataSource
import com.teck.data.repositories.datasource.room.DataBase
import com.teck.map_me.di.modules.RoomDbFactory
import com.teck.data.repositories.datasource.store.LocalStoreSourceImpl
import com.teck.domain.models.Place
import com.teck.domain.repository.Repository
import com.teck.domain.usecases.GetDataUseCase
import com.teck.domain.usecases.InputUseCase
import com.teck.domain.usecases.OutputUseCase
import com.teck.domain.usecases.SaveDataUseCase
import com.teck.ui.labels.LabelsFragment
import com.teck.ui.labels.LabelsViewModel
import com.teck.ui.map.MapFragment
import com.teck.ui.map.MapViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {
    fun storeModules() = module {
    }
    fun dataSourceModules() = module {
        single<DataBase>{ RoomDbFactory.createDb(get())}
        single<DataSource>{ LocalStoreSourceImpl(get<DataBase>().entityDao()) }
    }
    fun repositoriesModule() = module {
        single<Repository> { RepositoryImpl(get())}
    }
    fun usecasesModule() = module {
        factory<OutputUseCase<Flow<List<Place>>>> {
            GetDataUseCase(get())
        }

        factory<InputUseCase<Place>> {
            SaveDataUseCase(get())
        }
    }

    fun viewModelsModules() = module {
        scope<MapFragment> {
            viewModel{
                MapViewModel(get(), get())
            }
        }
        scope<LabelsFragment>{
            viewModel{
                LabelsViewModel(get(), get())
            }
        }
    }

}