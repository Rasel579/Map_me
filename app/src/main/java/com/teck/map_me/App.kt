package com.teck.map_me

import android.app.Application
import com.teck.map_me.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    Di.dataSourceModules(),
                    Di.repositoriesModule(),
                    Di.usecasesModule(),
                    Di.viewModelsModules()
                )
            )
        }
    }
}