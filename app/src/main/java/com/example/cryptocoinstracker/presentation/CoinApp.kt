package com.example.cryptocoinstracker.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptocoinstracker.data.workers.WorkerFactory
import com.example.cryptocoinstracker.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

     val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}