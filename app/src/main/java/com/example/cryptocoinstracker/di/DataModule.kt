package com.example.cryptocoinstracker.di

import android.app.Application
import android.content.Context
import com.example.cryptocoinstracker.data.database.AppDatabase
import com.example.cryptocoinstracker.data.database.CoinInfoDao
import com.example.cryptocoinstracker.data.repository.CoinRepositoryImpl
import com.example.cryptocoinstracker.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinDatabase(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}