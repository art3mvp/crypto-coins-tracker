package com.example.cryptocoinstracker.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.cryptocoinstracker.presentation.CoinDetailFragment
import com.example.cryptocoinstracker.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(application: Application)

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}