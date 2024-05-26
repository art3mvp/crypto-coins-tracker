package com.example.cryptocoinstracker.di

import android.app.Application
import com.example.cryptocoinstracker.presentation.CoinApp
import com.example.cryptocoinstracker.presentation.CoinDetailFragment
import com.example.cryptocoinstracker.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class, WorkerModule::class]
)
interface ApplicationComponent {

    fun inject(application: CoinApp)

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}