package com.example.cryptocoinstracker.presentation

import android.app.Application
import com.example.cryptocoinstracker.di.DaggerApplicationComponent

class CoinApp: Application() {

     val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}